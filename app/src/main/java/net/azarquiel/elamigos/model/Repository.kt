package net.azarquiel.elamigos.model

import android.app.Application
import androidx.lifecycle.LiveData

class AmigosRepository(application: Application) {

    val gruposDao = AmigosDB.getDatabase(application)!!.GruposDao()
    // select
    fun getAllGrupos(): LiveData<List<Grupos>> {
        return gruposDao.getAllGrupos()
    }
    // insert
    fun insertgrupos(grupos: Grupos) {
        gruposDao.insert(grupos)
    }
    // delete
    fun deletegrupos(id:Int) {
        gruposDao.delete(id)
    }
    // update
    fun updategrupos(grupos: Grupos) {
       gruposDao.update(grupos)
    }

    val amigosDao = AmigosDB.getDatabase(application)!!.AmigosDao()
    // select
    fun getAllAmigos(): LiveData<List<Amigos>> {
        return amigosDao.getAllProducts()
    }
    // insert
    fun insertamigos(amigos: Amigos) {
        amigosDao.insert(amigos)
    }
    // delete
    fun deleteamigos(id:Int) {
        amigosDao.delete(id)
    }
    // update
    fun updateamigos(amigos: Amigos) {
        amigosDao.update(amigos)
    }
}
