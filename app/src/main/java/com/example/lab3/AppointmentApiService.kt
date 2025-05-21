import com.example.lab3.Appointment
import com.example.lab3.AppointmentApi
import com.example.lab3.Wrapper
import com.example.lab3.data.api.RetrofitApiHelper
import com.example.lab3.data.model.IDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.lab3.di.DiHelper


class AppointmentApiService : IDataSource {

    private val api: AppointmentApi = RetrofitApiHelper().retrofit.create(AppointmentApi::class.java)

    val SECRET_KEY = "\$2a\$10\$SvHhTVN8U5bGtAuTHYF3euW1zvY4pue8ZY2lVCwty01HoKJhMc5WS"

    override fun getLocalAppointments(callback: IDataSource.AppointmentCallback) {
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