package ipca.grupo2.backend.tables

import com.google.firebase.firestore.ktx.toObject
import ipca.grupo2.backend.Backend
import ipca.grupo2.backend.models.Utilizador
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object BackendUtilizador {
    private var ref = "utilizadores";

    suspend fun login(email: String, password: String) : Boolean{
        var isSuccessful = false;

        Backend.getAU().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                isSuccessful = it.isSuccessful
            }

        return isSuccessful;
    }

    suspend fun getUtilizadorById(uid: String) : Utilizador?{
        var utilizador: Utilizador? = null;

        Backend.getFS().collection(ref).get().addOnSuccessListener { result ->
            for (doc in result){
                try{
                    var tempUtilizador = doc.toObject<Utilizador>()
                    if (tempUtilizador.getId() == uid){
                        utilizador = tempUtilizador
                        break;
                    }
                } catch (e: Exception){
                    // pls frontend
                }
            }
        }

        return utilizador;
    }

    suspend public fun getAllUtilizadoresByEvento(idEvento: String) : MutableList<Utilizador>{
        var mutableList : MutableList<Utilizador> = arrayListOf();

        // get all events and filter out the ones
        // we don't want
        GlobalScope.launch {
            var allEvents = BackendEventoUtilizador.getAllEventosUtilizadores();
            if (allEvents != null) {
                for (event in allEvents){
                    if (event.getIdEvento() == idEvento){
                        var tempUtilizador = getUtilizadorById(event.getIdUtilizador()!!)
                        mutableList.add(tempUtilizador!!)
                    }
                }
            }
        }

        return mutableList;
    }

    public fun getRef() : String{
        return this.ref;
    }
}