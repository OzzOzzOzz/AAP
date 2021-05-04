package fr.iamissam.bieredex.ui.map

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import fr.iamissam.bieredex.data.models.BeerData
import fr.iamissam.bieredex.data.repository.BeerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MapViewModel(application: Application) : AndroidViewModel(application) {
    val markers = MutableLiveData<List<LatLng>>()

    fun fetchData() {
        markers.value = listOf(
            LatLng(43.283261999538084, 5.43233166691913),
            LatLng(43.28048146790086, 5.418384180853734),
            LatLng(43.28091886332266, 5.40804158349447),
            LatLng(43.28363689295801, 5.411903964251042),
            LatLng(43.28897875130756, 5.45104049728133),
            LatLng(43.28559640582155, 5.397623224986497),
            LatLng(43.286933821684464, 5.393744547526287),
            LatLng(43.27147744508183, 5.406809566339625),
            LatLng(43.267166793846854, 5.408442693691292),
            LatLng(43.26062591243632, 5.416812471368586)
        )
    }
}