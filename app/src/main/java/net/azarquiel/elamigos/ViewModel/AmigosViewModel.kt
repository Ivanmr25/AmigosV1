package net.azarquiel.elamigos.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.elamigos.model.Amigos
import net.azarquiel.elamigos.model.AmigosRepository
import net.azarquiel.elamigos.model.Grupos

class AmigosViewModel (application: Application) : AndroidViewModel(application) {

    private var repository: AmigosRepository = AmigosRepository(application)

    fun getAllGrupos(): LiveData<List<Grupos>>{
        return repository.getAllGrupos()
    }

    fun insertg(grupos: Grupos) {
        GlobalScope.launch() {
            repository.insertgrupos(grupos)
            launch(Dispatchers.Main) {
            }
        }
    }

    fun deleteg(id: Int) {
        GlobalScope.launch() {
            repository.deletegrupos(id)
            launch(Dispatchers.Main) {
            }
        }
    }

    fun updateg(grupos: Grupos) {
        GlobalScope.launch() {
            repository.updategrupos(grupos)
            launch(Dispatchers.Main) {
            }
        }
    }
    fun getAllAmigos(): LiveData<List<Amigos>>{
        return repository.getAllAmigos()
    }

    fun insertA(amigos: Amigos) {
        GlobalScope.launch() {
            repository.insertamigos(amigos)
            launch(Dispatchers.Main) {
            }
        }
    }

    fun delete(id: Int) {
        GlobalScope.launch() {
            repository.deleteamigos(id)
            launch(Dispatchers.Main) {
            }
        }
    }

    fun update(amigos: Amigos) {
        GlobalScope.launch() {
            repository.updateamigos(amigos)
            launch(Dispatchers.Main) {
            }
        }
    }
}
