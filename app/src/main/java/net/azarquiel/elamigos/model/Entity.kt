package net.azarquiel.elamigos.model


import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "grupo")
data class Grupos(@PrimaryKey(autoGenerate = true)

                    var idgrupo: Int=0,

                    var nombre:String="",

                    var email:String="",
                  var color: Int=0,
                    )

@Entity(tableName = "amigo",
    foreignKeys = [ForeignKey(entity = Grupos::class,
        parentColumns = arrayOf("idgrupo"),
        childColumns = arrayOf("id"))])
data class Amigos(@PrimaryKey(autoGenerate = true)

                  var id: Int=0,

                  var nombre:String="",
                  var grupo :Int = 0,
                  var colorfriend: Int=0,
)

