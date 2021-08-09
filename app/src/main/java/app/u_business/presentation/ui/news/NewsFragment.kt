package app.u_business.presentation.ui.news

import app.u_business.R
import app.u_business.databinding.FrNewsBinding
import app.u_business.databinding.ItemSmallNewsBinding
import app.u_business.domain.model.News
import app.u_business.presentation.ui.base.BaseFragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment(override val layoutId: Int = R.layout.fr_news) : BaseFragment<FrNewsBinding>() {
    //private val adapter: NewsAdapter by lazy { NewsAdapter() }

    private val adapter: BaseAdapter<News, ItemSmallNewsBinding> by lazy {
        BaseAdapter(
            { li, parent, attach -> ItemSmallNewsBinding.inflate(li, parent, attach) },
            { binding, news ->
                with(binding) {
                    Glide.with(root).load(R.drawable.test_image)
                        .circleCrop().into(itemSmallNewsImage)

                    itemSmallNewsTitleText.text = news.title
                    itemSmallNewsDateText.text = news.date
                    itemSmallNewsContentText.text = news.content
                }
            }
        )
    }
    private val newsVm by viewModel<NewsVM>()

    override fun initViews() = initRv()

    private fun initRv() {
        binding.newsRv.adapter = adapter.setWithBannerTest()
        newsVm.ldFetchNews.observe(viewLifecycleOwner) {
            renderData(it, R.id.progress_news, { adapter.data = it.data.toNews() })
        }
        binding.newsSearchTil.setEndIconOnClickListener { newsVm.requestSearchNews(binding.newsSearchTil.editText?.text.toString()) }
        newsVm.requestNewsList()
    }
}