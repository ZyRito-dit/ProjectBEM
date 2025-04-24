package com.example.projectbem.Home.dashboard_item.Gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.projectbem.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ImageDetailBottomSheet : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(
            nama: String,
            waktu: String,
            dimensi: String,
            ukuran: String,
            lokasi: String
        ): ImageDetailBottomSheet {
            val fragment = ImageDetailBottomSheet()
            val args = Bundle().apply {
                putString("nama", nama)
                putString("waktu", waktu)
                putString("dimensi", dimensi)
                putString("ukuran", ukuran)
                putString("lokasi", lokasi)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_image_detail_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nama = arguments?.getString("nama")
        val waktu = arguments?.getString("waktu")
        val dimensi = arguments?.getString("dimensi")
        val ukuran = arguments?.getString("ukuran")
        val lokasi = arguments?.getString("lokasi")

        view.findViewById<TextView>(R.id.txtNama).text = nama
        view.findViewById<TextView>(R.id.txtWaktu).text = waktu
        view.findViewById<TextView>(R.id.txtDimensi).text = dimensi
        view.findViewById<TextView>(R.id.txtUkuran).text = ukuran
        view.findViewById<TextView>(R.id.txtLokasi).text = lokasi
    }
}
