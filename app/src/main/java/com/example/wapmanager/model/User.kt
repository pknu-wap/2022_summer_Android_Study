package com.example.wapmanager.model

class User {
    var studentId: String = ""
    var studentName: String = ""
    var clubUpDate: Long = 0

    constructor()

    constructor(studentId:String, studentName:String, clubUpDate:Long){
        this.studentId = studentId
        this.studentName = studentName
        this.clubUpDate = clubUpDate
    }
}