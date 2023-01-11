package com.sophos.documentmanager

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sophos.documentmanager.ui.navigation.AppNavigation
import com.sophos.documentmanager.ui.theme.SophosLight
import com.sophos.documentmanager.ui.theme.SophosLightDisable
import com.sophos.documentmanager.ui.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity @Inject constructor() : AppCompatActivity(), OnMapReadyCallback {
    private val loginViewModel: LoginViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()
    private val documentShowViewModel: DocumentShowViewModel by viewModels()
    private val documentCreateViewModel: DocumentCreateViewModel by viewModels()
    private val officeShowViewModel: OfficeShowViewModel by viewModels()
    private val imageShowViewModel: ImageShowViewModel by viewModels()

    private var canAuthenticate = false
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var map: GoogleMap
    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AppNavigation(
                loginViewModel = loginViewModel,
                homeViewModel = homeViewModel,
                documentCreateViewModel = documentCreateViewModel,
                documentShowViewModel = documentShowViewModel,
                officeShowViewModel = officeShowViewModel,
                imageShowViewModel = imageShowViewModel
            )
        }
//        setupAuth()
    }
    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableMyLocation() {
        if (!::map.isInitialized) return
        if (isPermissionsGranted()) {
//            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
        enableMyLocation()
    }

    private fun createMarker() {
        val coordinates = LatLng(6.218229100000025, -75.58021739999998)
        val marker = MarkerOptions().position(coordinates).title("sophos medellin")
        map.addMarker(marker)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 18f), 4000, null
        )
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED


    @Composable
    fun huella() {
        authenticate { }
    }

    private fun setupAuth() {
        Log.d(
            "DeviceCredential", BiometricManager.from(this)
                .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG).toString()
        )
        if (BiometricManager.from(this)
                .canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
        ) {
            canAuthenticate = true

            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Authenticate Biometric")
                .setSubtitle("Authenticate yourself using the biometric sensor")
                .setNegativeButtonText("Enter your Sophos credentials")
                .build()
        }
    }

    private fun authenticate(auth: (Boolean) -> Unit) {
        if (canAuthenticate) {
            BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        auth(true)
                    }
                }).authenticate(promptInfo)
        } else {
            auth(true)
        }
    }

    @Composable
    fun FingerPrintLoginButton() {
        var auth by remember { mutableStateOf(false) }
        Column() {
            Button(
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    authenticate { auth = it }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    disabledBackgroundColor = Color.White,
                    contentColor = SophosLight,
                    disabledContentColor = SophosLightDisable,
                ),
                border = BorderStroke(1.dp, SophosLight)

            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.fingerprint),
                    contentDescription = "Icon finger print login"
                )
                Text(text = "Ingresar con huella")
            }
        }

    }
}
