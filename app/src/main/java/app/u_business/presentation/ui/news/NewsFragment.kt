package app.u_business.presentation.ui.news

import app.u_business.R
import app.u_business.databinding.FrNewsBinding
import app.u_business.presentation.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment(override val layoutId: Int = R.layout.fr_news) : BaseFragment<FrNewsBinding>() {
    private val adapter: NewsAdapter by lazy { NewsAdapter() }
    private val newsVm by viewModel<NewsVM>()

    override fun initViews() = initRv()

    private fun initRv() {
        binding.newsRv.adapter = adapter
        newsVm.ldFetchNews.observe(viewLifecycleOwner) {
            renderData(it, R.id.progress_news, { adapter.data = it.data.toNews() })
        }
        binding.newsSearchTil.setEndIconOnClickListener { newsVm.requestSearchNews(binding.newsSearchTil.editText?.text.toString()) }
        newsVm.requestNewsList()
    }
}