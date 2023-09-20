package com.example.newsapp.ui.home.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.api.Constant
import com.example.newsapp.api.model.newsResponse.News
import com.example.newsapp.api.model.sourcesResponse.Sources
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.home.fullnews.FullNewsActivity
import com.example.newsapp.ViewError
import com.example.newsapp.ui.home.SettingsActivity
import com.example.newsapp.showMessage
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class NewsFragment : Fragment() {
    lateinit var viewBinding: FragmentNewsBinding
    lateinit var viewModel: NewsViewModel
    var pageSize =20
    var currentPage = 1
    lateinit var sourceObj: Sources
    var isLoading : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentNewsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        viewModel.getNewsSources()

    }

    private fun initObservers() {
//        viewModel.shouldShowLoading
//            .observe(viewLifecycleOwner)
//            { value -> viewBinding.progressBar.isVisible = value }
        viewModel.soucrcesLiveData
            .observe(viewLifecycleOwner) {
                bindTabs(it)
                getNews()
            }
        viewModel.newsLiveData
            .observe(viewLifecycleOwner) {
                adapter.bindNews(it)
            }
        viewModel.viewError
            .observe(viewLifecycleOwner) {
                handleError(it)
            }
    }

    private fun getNews() {
        viewModel.getNews(sourceObj.id, pageSize = pageSize, page =currentPage )
        isLoading = false
    }

    var adapter = NewsAdapter()

    private fun initViews() {
        sideMenu()
        viewBinding.vm = viewModel
        viewBinding.lifecycleOwner = this
        viewBinding.recyclerView.adapter = adapter
        adapter.onItemClickListner = object :NewsAdapter.OnItemClickListner{
            override fun onClick(position: Int, news: News) {
                showData(news)
            }

        }
        viewBinding.recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManger = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemCount = layoutManger.findLastVisibleItemPosition()
                val totalItemCount = layoutManger.itemCount
                val visableThreshold = 3
                if (isLoading&& totalItemCount- lastVisibleItemCount<= visableThreshold){
                    isLoading = true
                    currentPage++
                    getNews()
                }
            }
        })

    }

    private fun sideMenu() {


        viewBinding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.catego -> {
                    Toast.makeText(requireContext(), "Catego", Toast.LENGTH_SHORT).show()
                }
                R.id.settings ->{
                    Toast.makeText(requireContext(), "Settings", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(),SettingsActivity::class.java)
                    startActivity(intent)
                }
            }
            true
        }
    }


    fun showData(news: News) {
        val intent = Intent(requireContext(), FullNewsActivity::class.java)
        intent.putExtra(Constant.OBJ_KEY,news)
        startActivity(intent)
    }

    private fun bindTabs(sources: List<Sources?>?) {
        if (sources == null) return
        sources.forEach {
            val tab = viewBinding.tabLayout.newTab()
            tab.text = it?.name
            tab.tag = it
            viewBinding.tabLayout.addTab(tab)
        }
        viewBinding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Sources
                sourceObj = source
                viewModel.getNews(sourceObj.id, pageSize = pageSize, page =currentPage )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Sources
                sourceObj = source
                viewModel.getNews(sourceObj.id, pageSize = pageSize, page =currentPage )
            }

        })
        viewBinding.tabLayout.getTabAt(0)?.select()

    }


    fun handleError(viewError: ViewError) {
        showMessage(message = viewError.message ?: viewError.t?.localizedMessage
        ?: "something Wrong",
            posActionName = "Try Again",
            posAction = { dialogInterface, i ->
                dialogInterface.dismiss()
                viewError.onTryAgainClickListener?.onTryAgainClicked()
            },
            negActionName = "Cancel",
            negAction = { dialogInterface, i ->
                dialogInterface.dismiss()
            })
    }
}