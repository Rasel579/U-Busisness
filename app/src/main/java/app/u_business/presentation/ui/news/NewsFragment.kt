package app.u_business.presentation.ui.news

import app.u_business.R
import app.u_business.databinding.FrNewsBinding
import app.u_business.domain.model.News
import app.u_business.presentation.ui.base.BaseFragment

class NewsFragment(override val layoutId: Int = R.layout.fr_news) : BaseFragment<FrNewsBinding>() {
    private val adapter: NewsAdapter by lazy { NewsAdapter() }

    override fun initViews() {
        initRv()
        mockAdapter()
    }

    private fun initRv() {
        binding.newsRv.adapter = adapter
    }

    private fun mockAdapter() {
        adapter.data = listOf(
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),
            News(
                "",
                "Мы принимаем участие в научном исследовании",
                "18 февраля 19:00",
                "Разнообразный и богатый опыт djsfksdkfldsflsdfdsfkdskfkdsk"
            ),

        )
    }
}