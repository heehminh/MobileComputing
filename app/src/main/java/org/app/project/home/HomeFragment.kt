package org.app.project.home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import org.app.project.MainActivity
import org.app.project.R
import org.app.project.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var movieDatas = ArrayList<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        movieDatas.apply{
            add(Movie("콜", R.drawable.movie01, true, "2020년 11월 27일에 넷플릭스를 통해 공개된 한국 영화. 서로 다른 시간에 살고 있는 두 여자가 한 통의 전화로 연결되며 벌어지는 이야기를 그린 스릴러물. 2015년에 몸 값이라는 단편 영화로 단편영화제에서 두각을 나타낸 이충현 감독의 장편 데뷔작이다.원래 2020년 3월 개봉 예정이었으나, 코로나19 사태로 인해 극장에서의 개봉을 취소하고 넷플릭스에서 오리지널 영화로 독점 공개했다.# 사냥의 시간 이후 두 번째로 극장 개봉 없이 넷플릭스에서 독점공개되는 국내 영화다."))
            add(Movie("모가디슈", R.drawable.movie02, false, "\"제 42회 청룡영화상 올해 가장 흥행한 영화 및 미술 부분, 연출 부분, 최우수 작품 부분 수상작\"\\n" +"2021년 7월 28일에 개봉한 한국 영화. 류승완의 11번째 연출작이기도 하다. 1991년 소말리아 내전 당시, 대한민국과 북한의 대사관 공관원들이 고립된 뒤 함께 목숨을 걸고 소말리아의 수도인 모가디슈를 탈출했던 실제 사건을 모티브로 제작되었으며, 대한민국 외교공관 철수를 주제로 촬영한 한국 최초의 영화이기도 하다."))
            add(Movie("사이버 지옥: n번방을 무너뜨려라", R.drawable.movie03, true, "n번방 성착취물 제작 및 유포 사건을 소재로 한 넷플릭스 오리지널 다큐멘터리"))
            add(Movie("기생충", R.drawable.movie04, true, "\"이 영화는 악인이 없으면서도 비극이고, 광대가 없는데도 희극이다.\" \\n2019년에 개봉된 봉준호 감독의 7번째 장편 영화. 상류층과 하류층. 두 가족의 만남을 다룬 대한민국의 블랙 코미디 가족 드라마 영화다."))
            add(Movie("엑시트", R.drawable.movie05, false, "짠내 폭발 청년백수, 전대미문의 진짜 재난을 만나다!\n" + "대학교 산악 동아리 에이스 출신이지만\n" + "졸업 후 몇 년째 취업 실패로 눈칫밥만 먹는 용남은\n" + "온 가족이 참석한 어머니의 칠순 잔치에서\n" + "연회장 직원으로 취업한 동아리 후배 의주를 만난다\n" + "어색한 재회도 잠시, 칠순 잔치가 무르익던 중\n" + "의문의 연기가 빌딩에서 피어 오르며\n" + "피할 새도 없이 순식간에 도심 전체는 유독가스로 뒤덮여 일대혼란에 휩싸이게 된다.\n" + "용남과 의주는 산악 동아리 시절 쌓아 뒀던 모든 체력과 스킬을 동원해\n" + "탈출을 향한 기지를 발휘하기 시작하는데…"))
            add(Movie("시동", R.drawable.movie06, false, "정체불명 단발머리 주방장 ‘거석이형’(마동석)을 만난 어설픈 반항아 ‘택일’(박정민)과\n" + "무작정 사회로 뛰어든 의욕충만 반항아 ‘상필’(정해인)이 진짜 세상을 맛보는 유쾌한 이야기를 그린 작품이다."))
        }


        // 더미데이터와 Adapter 연결
        val movieRVAdapter = MovieRVAdapter(movieDatas)
        binding.homeRecyclerview.adapter = movieRVAdapter
        binding.homeRecyclerview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        movieRVAdapter.setMyItemClickListener(object: MovieRVAdapter.MyItemClickListener{
            override fun onItemClick(movie: Movie) {
                changeMoreFragment(movie)
            }

            private fun changeMoreFragment(movie: Movie) {
                (context as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm, MoreFragment().apply {
                        arguments = Bundle().apply {
            //                            putString("title", movie.title)
            //                            putInt("img", movie.image)
            //                            putBoolean("like", movie.like)
                            val gson = Gson()
                            val movieJson = gson.toJson(movie)
                            putString("title", movieJson)
                        }
                    })
                    .commitAllowingStateLoss()
            }

            override fun onRemoveMovie(position: Int) {
                movieRVAdapter.removeItem(position)
            }
        })

        return binding.root
    }


}



