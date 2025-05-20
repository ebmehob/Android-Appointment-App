import com.example.lab3.Appointment
import com.example.lab3.AppointmentApi
import com.example.lab3.Wrapper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppointmentApiService {
    var api: AppointmentApi
    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jsonbin.io/v3/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(AppointmentApi::class.java)
    }



    val SECRET_KEY = "\$2a\$10\$SvHhTVN8U5bGtAuTHYF3euW1zvY4pue8ZY2lVCwty01HoKJhMc5WS"
    fun getLocalAppointments(callback: AppointmentCallback) {
        api.getAppointments(SECRET_KEY).enqueue(object : Callback<Wrapper> {
            override fun onResponse(call: Call<Wrapper>, response:
            Response<Wrapper>) {
                if (response.code() == 200 && response.body() != null)
                    callback.onSuccess(response.body()!!.record)
                else
                    callback.onFailure()
            }
            override fun onFailure(call: Call<Wrapper>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
    interface AppointmentCallback {
        fun onSuccess(appoinment: List<Appointment>)
        fun onFailure()
    }
}