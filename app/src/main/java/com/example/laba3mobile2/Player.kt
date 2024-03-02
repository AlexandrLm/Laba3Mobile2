package com.example.laba3mobile2

import android.os.Parcel
import android.os.Parcelable

class Player(_name: String) : Parcelable {
    var name: String
    init {
        name = _name
    }
    var lose: Boolean = false
    var win: Boolean = false
    var howMuchWordsType: Int = 0

    constructor(parcel: Parcel) : this(parcel.readString()!!) {
        lose = parcel.readByte() != 0.toByte()
        win = parcel.readByte() != 0.toByte()
        howMuchWordsType = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (lose) 1 else 0)
        parcel.writeByte(if (win) 1 else 0)
        parcel.writeInt(howMuchWordsType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
