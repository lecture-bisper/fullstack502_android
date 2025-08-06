package btic.fullstack502.app_20250806.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import android.util.Log

//  SQLiteOpenHelper : 안드로이드 기본 라이브러리, SQLite 를 쉽게 사용할 수 있도록 도와주는 추상 클래스
//  SQLiteOpenHelper 를 상속받아 사용
//  두번째 매개변수 : 안드로이드 내부에서 사용하는 sqlite 파일명
//  네번재 매개변수 : 사용자가 지정하는 데이터로 데이터베이스 버전을 의미
class DBHelper(context: Context) : SQLiteOpenHelper(context, "testdb.sqlite", null, 2) {
  
//  앱 설치 시 단 한번만 실행되는 메소드
//  주로 데이터베이스 테이블 생성 명령을 입력
  override fun onCreate(db: SQLiteDatabase?) {
    db?.execSQL("CREATE TABLE phonebook (\n" +
      "seq      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, \n" +
      "name     TEXT NOT NULL, \n" +
      "phone    TEXT NOT NULL, \n" +
      "email    TEXT, \n" +
      "addr     TEXT) ")

    Log.d("**fullstack502**", "### 앱 설치 시 한번만 실행되는 onCreate() 호출 ###")
  }

//  앱 버전 정보가 변경될 경우 호출되는 메소드
  override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int ) {
    Log.d("**fullstack502**", "### 데이터베이스 변경 시 계속 실행되는 onUpgrade() 호출 ###")
  }

//  사용자가 생성한 데이터베이스 제어용 메소드
  fun dbInsert(db: SQLiteDatabase?, member: PhoneBookData) {
    
//    execSQL() : insert, update, delete 쿼리문을 실행하는데 사용하는 메소드
//    첫번째 매개변수 : 실행할 SQL 쿼리문
//        SQL 쿼리문에 데이터가 모두 포함되어 있을 경우 두번째 매개변수를 생략할 수 있음
//    두번째 매개변수 : 실행할 SQL 쿼리문에 포함된 ' ? ' 기호에 입력할 데이터, 데이터는 배열 타입
//        쿼리문의 ' ? ' 순서대로 배열의 데이터가 대입 됨, 0 부터 시작
    val sql = "INSERT INTO phonebook (name, phone, email, addr) " +
        "VALUES ('${member.name}', '${member.phone}', '${if (member.email != null) member.email else null}', '${if (member.addr != null) member.addr else null}')"
    db?.execSQL(sql)
  }

  fun dbUpdate(db: SQLiteDatabase?, member: PhoneBookData) {
    val sql = "UPDATE phonebook SET " +
        "name = ?, " +
        "phone = ?, " +
        "email = ?, " +
        "addr = ? " +
        "WHERE seq = ? "
    db?.execSQL(sql, arrayOf(member.name, member.phone, member.email, member.addr, member.seq))
  }

  fun dbDelete(db: SQLiteDatabase?, seq: Int) {
    val sql = "DELETE FROM phonebook " +
        "WHERE seq = $seq "
    db?.execSQL(sql)
  }

  fun dbSelectSeq(db: SQLiteDatabase?, seq: Int) : String {
//    rawQuery() : select 쿼리문을 실행하는 메소드, 조회 결과로 Cursor 타입의 객체를 출력
//    첫번째 매개변수 : 실행할 SQL 쿼리문
//    두번째 매개변수 : SQL 쿼리문에 포함된 ' ? ' 기호에 대입될 데이터, 배열 타입, 입력할 데이터가 없으면 null 입력
    val sql = "SELECT seq, name, phone, email, addr FROM phonebook " +
        "WHERE seq = $seq "
    val data = db?.rawQuery(sql, null)

    var result = ""

//    moveToNext() : 조회 결과로 받은 Cursor 객체에서 다음 데이터가 있는지 확인하는 메소드
//    getInt() : moveToxxx() 를 사용하여 가져온 데이터 객체에서 지정한 index의 데이터를 가져오는 메소드, Int 타입으로 출력
//    getString() : moveToxxx() 를 사용하여 가져온 데이터 객체에서 지정한 index의 데이터를 가져오는 메소드, String 타입으로 출력
    while (data!!.moveToNext()) {
      result += "번호 : ${data.getInt(0)} \n" +
          "이름 : ${data.getString(1)} \n" +
          "휴대폰 : ${data.getString(2)} \n" +
          "이메일 : ${data.getString(3)} \n" +
          "주소 : ${data.getString(4)}"
    }

    return result
  }

  fun dbSelectName(db: SQLiteDatabase?, name: String) : String {
    val sql = "SELECT seq, name, phone, email, addr FROM phonebook " +
        "WHERE name like '%$name%' "
    val data = db?.rawQuery(sql, null)
    var result = ""

    while (data!!.moveToNext()) {
      result += "번호 : ${data.getInt(0)} \n" +
          "이름 : ${data.getString(1)} \n" +
          "휴대폰 : ${data.getString(2)} \n" +
          "이메일 : ${data.getString(3)} \n" +
          "주소 : ${data.getString(4)}"
    }

    return result
  }

  fun dbSelectAll(db: SQLiteDatabase?) : String {
    val sql = "SELECT seq, name, phone, email, addr FROM phonebook "
    val data = db?.rawQuery(sql, null)

    var result = ""

    while (data!!.moveToNext()) {
      result += "번호 : ${data.getInt(0)} \n" +
          "이름 : ${data.getString(1)} \n" +
          "휴대폰 : ${data.getString(2)} \n" +
          "이메일 : ${data.getString(3)} \n" +
          "주소 : ${data.getString(4)} \n" +
          "------------------------------- \n"
    }

    return result
  }
}









