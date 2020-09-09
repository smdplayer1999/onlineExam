package com.mysoftpanda.android.onlineexam.data.retrofit


import com.mysoftpanda.android.onlineexam.R
import com.mysoftpanda.android.onlineexam.data.entities.*
import com.mysoftpanda.android.onlineexam.data.retrofit.extensions.ResultData
import com.mysoftpanda.android.onlineexam.data.local.LocalStorage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
val l = LocalStorage.instance
private val api = ApiClient.retrofit.create(ContactApi::class.java)
private val apiCard = ApiClient.retrofit.create(CardApi::class.java)
fun contactLoader( block: (ResultData<ResponseData<List<ContactReturnedData>>>) -> Unit){
    api.getAll().enqueue(object :Callback<ResponseData<List<ContactReturnedData>>>{
        override fun onFailure(call: Call<ResponseData<List<ContactReturnedData>>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(
            call: Call<ResponseData<List<ContactReturnedData>>>,
            response: Response<ResponseData<List<ContactReturnedData>>>
        ) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res))
            }
        }
    })
}
fun addContact(newContact: ContactData, block: (ResultData<ContactReturnedData>) -> Unit){

    api.add(newContact).enqueue(object : Callback<ResponseData<ContactReturnedData>> {
        override fun onFailure(call: Call<ResponseData<ContactReturnedData>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(call: Call<ResponseData<ContactReturnedData>>, response: Response<ResponseData<ContactReturnedData>>) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res.data!!))
            }
        }
    })

}
fun cardLoader( block: (ResultData<ResponseData<List<CardData>>>) -> Unit){
    apiCard.getAllCard().enqueue(object :Callback<ResponseData<List<CardData>>>{
        override fun onFailure(call: Call<ResponseData<List<CardData>>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(
            call: Call<ResponseData<List<CardData>>>,
            response: Response<ResponseData<List<CardData>>>
        ) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res))
            }
        }
    })
}
fun addCard(newContact: AddCardData, block: (ResultData<Any>) -> Unit){

    apiCard.addCard(newContact).enqueue(object : Callback<ResponseData<Any>> {
        override fun onFailure(call: Call<ResponseData<Any>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(call: Call<ResponseData<Any>>, response: Response<ResponseData<Any>>) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res.data!!))
            }
        }
    })

}
fun verifyCard(code: String, block: (ResultData<CardData>) -> Unit) {
    apiCard.verifyCard( VerifyCard(localStorage.cardPan, code))
        .enqueue(object : Callback<ResponseData<CardData>> {
            override fun onFailure(call: Call<ResponseData<CardData>>, t: Throwable) {
                block(ResultData.failure(t))
            }

            override fun onResponse(
                call: Call<ResponseData<CardData>>,
                response: Response<ResponseData<CardData>>
            ) {
                val res = response.body()
                when {
                    res == null -> block(ResultData.resource(R.string.empty_body))
                    res.status == "ERROR" -> block(ResultData.message(res.message))
                    res.status == "OK" -> block(ResultData.data(res.data!!))
                }

            }
        })
}
fun removeContact(contact: ContactData, block: (ResultData<ContactReturnedData>) -> Unit){
    api.remove(contact).enqueue(object : Callback<ResponseData<ContactReturnedData>> {

        override fun onFailure(call: Call<ResponseData<ContactReturnedData>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(
            call: Call<ResponseData<ContactReturnedData>>,
            response: Response<ResponseData<ContactReturnedData>>
        ) {            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res.data!!))
            }
        }
    })
}
fun updateContact(contact: ContactData, block: (ResultData<ContactReturnedData>) -> Unit){
    api.update(contact).enqueue(object : Callback<ResponseData<ContactReturnedData>> {
        override fun onFailure(call: Call<ResponseData<ContactReturnedData>>, t: Throwable) {
            block(ResultData.failure(t))
        }

        override fun onResponse(
            call: Call<ResponseData<ContactReturnedData>>,
            response: Response<ResponseData<ContactReturnedData>>
        ) {
            val res = response.body()
            when {
                res == null -> block(ResultData.resource(R.string.empty_body))
                res.status == "ERROR" -> block(ResultData.message(res.message))
                res.status == "OK" -> block(ResultData.data(res.data!!))
            }
        }
    })
}