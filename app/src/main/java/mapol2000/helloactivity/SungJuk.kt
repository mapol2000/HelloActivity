package mapol2000.helloactivity

import android.os.Parcel
import android.os.Parcelable

// 다른 액티비티에 객체형태로 전달하기 위해
// Parcelable 인터페이스를 구현해서 재작성함
data class SungJuk(var name: String, var kor: String,  var eng: String, var mat: String): Parcelable {

    var tot: String = "0"
    var avg: String = "0.0"
    var grd: String = "가"

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
        tot = parcel.readString().toString()
        avg = parcel.readString().toString()
        grd = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(kor)
        parcel.writeString(eng)
        parcel.writeString(mat)
        parcel.writeString(tot)
        parcel.writeString(avg)
        parcel.writeString(grd)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SungJuk> {
        override fun createFromParcel(parcel: Parcel): SungJuk {
            return SungJuk(parcel)
        }

        override fun newArray(size: Int): Array<SungJuk?> {
            return arrayOfNulls(size)
        }
    }

}