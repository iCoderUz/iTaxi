package icoder.itaxi.uz.model

class Client(){
    private lateinit var key: String
    private lateinit var phone: String
    private lateinit var password: String
    constructor(key: String, phone: String, password: String) : this() {
        this.key = key
        this.phone = phone
        this.password = password
    }
}