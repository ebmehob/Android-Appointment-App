package com.example.lab3.di

import com.example.lab3.data.model.AppointmentApiService
import com.example.lab3.data.model.IDataSource
import com.example.lab3.data.api.RetrofitApiHelper
import com.example.lab3.uii.SecondContract
import com.example.lab3.uii.SecondPresenter

class DiHelper {

    companion object {
        private var mainPresenter: SecondContract.Presenter? = null
        private var service: IDataSource? = null
        private var retrofitHelper: RetrofitApiHelper? = null

        fun getPresenter(view: SecondContract.View): SecondContract.Presenter {
            if (mainPresenter == null) {
                mainPresenter = SecondPresenter(view)
            }
            return mainPresenter!!
        }

        fun getService(): IDataSource {
            if (service == null) {
                service = AppointmentApiService()
            }
            return service!!
        }

        fun getRetrofitHelper(): RetrofitApiHelper {
            if (retrofitHelper == null) {
                retrofitHelper = RetrofitApiHelper()
            }
            return retrofitHelper!!
        }

    }
}