package campus.tech.kakao.map.model.datasource

import android.util.Log
import androidx.core.content.ContextCompat.getString
import campus.tech.kakao.map.R
import campus.tech.kakao.map.model.Location
import campus.tech.kakao.map.model.LocationDto
import campus.tech.kakao.map.retrofit.KakaoAPI
import campus.tech.kakao.map.retrofit.RetrofitInstance
import com.android.identity.BuildConfig
import kotlinx.coroutines.Dispatchers

class LocationRemoteDataSource {
    companion object{
        private const val RESULT_SIZE = 15
    }

    private val client = RetrofitInstance.getInstance().create(KakaoAPI::class.java)

    suspend fun getLocations(keyword: String): List<Location> {
        val kakaoRestApiKey = "KakaoAK " + campus.tech.kakao.map.BuildConfig.KAKAO_REST_API_KEY

        val response = client.searchFromKeyword(kakaoRestApiKey, keyword, RESULT_SIZE)
        val locationDtos: List<LocationDto> = response.body()?.documents ?: emptyList()
        Log.d("jieun", "locationDtos: " + locationDtos)

        return toLocations(locationDtos)
    }

    private fun toLocations(locationDtos: List<LocationDto>) =
        locationDtos.map {
            Location.toLocation(it)
        }
}