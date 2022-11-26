package net.azarquiel.elamigos.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.Update

@Dao
interface GruposDao {
    @Query("SELECT * from grupo ORDER BY nombre ASC")
    fun getAllGrupos(): LiveData<List<Grupos>>



    @Insert
    fun insert(grupos: Grupos)

    @Query("DELETE FROM grupo WHERE idgrupo=:id")
    fun delete(id:Int)

    @Update
    fun update(grupos: Grupos)
}
@Dao
interface AmigosDao {
    @Query("SELECT * from amigo ORDER BY nombre ASC")
    fun getAllProducts(): LiveData<List<Amigos>>



    @Insert
    fun insert(amigos: Amigos)

    @Query("DELETE FROM amigo WHERE id=:id")
    fun delete(id:Int)

    @Update
    fun update(amigos: Amigos)
}
