package ch2ps299.ajiananta.nutriwise.model

import ch2ps299.ajiananta.nutriwise.R

object RecipesDataList {
    val RecipesData = listOf(
        Recipe(
            id = 1,
            image = R.drawable.bubur_ayam_tomat,
            name = "Bubur Ayam Tomat",
            tag = listOf(
                "Makanan Utama",
                "Ayam"
            ),
            portion = 4,
            age = "8-12 Bulan",
            ingredients = "1/2 sdt butter\n" +
                    "1 sdm bawang bombai\n" +
                    "30 gr tahu\n" +
                    "3 buah tomat ceri\n" +
                    "5 sdm nasi\n" +
                    "50 ml kaldu ayam\n" +
                    "100 ml air\n",
            steps = "1. Lelehkan mentega bersamaan dengan menumis bawang bombay yang sudah dicincang hingga harum\n" +
                    "2. Potong kecil-kecil ayam dan tahu lalu masukan ke dalam tumisan dan aduk hingga rata\n" +
                    "3. Tambahkan air\n" +
                    "4. Potong dadu tomat ceri dan masukan nasi kedalam tumisan\n" +
                    "5. Aduk-aduk tunggu hingga menyusut lalu angkat\n" +
                    "6. Blender makanan tersebut lalu sajikan.\n",
            nutrition = listOf(
                Pair("Energi", "67 kkal"),
                Pair("Lemak", "3 g"),
                Pair("Protein", "3 g"),
                Pair("Karbohidrat", "8 g"),
                Pair("Serat", "0.2 g"),
                Pair("Zat Besi", "0.4 mg"),
            )
        ),
        Recipe(
            id = 2,
            image = R.drawable.bubur_kentang_daging,
            name = "Bubur Kentang Daging",
            tag = listOf(
                "Makanan Utama",
                "Bubur",
                "Kentang",
                "Daging"
            ),
            portion = 4,
            age = "8-12 Bulan",
            ingredients = "150 gr kentang\n" +
                    "30 gr daging sapi giling\n" +
                    "1 siung bawang merah\n" +
                    "1 siung bawang putih\n" +
                    "2 helai daun sop\n" +
                    "170 ml air\n" +
                    "30 ml air santan\n" +
                    "Margarin dan garam\n",
            steps = "1. Potong dadu kentang dan cincang halus bawang merah, bawang putih dan daun sop\n" +
                    "2. Tumis bawang merah dan putih dengan margarin\n" +
                    "3. Masukan daging sapi lalu tumis hingga matang\n" +
                    "4. Tambahkan air\n" +
                    "5. Masukan kentang, lalu aduk rata, tutup dan tunggu hingga menyusut\n" +
                    "6. Tambahkan santan berbarengan dengan memasukan daun sop lalu aduk hingga rata\n" +
                    "7. Angkat makanan tersebut dan blender lalu sajikan \n",
            nutrition = listOf(
                Pair("Energi", "68 kkal"),
                Pair("Lemak", "3 g"),
                Pair("Protein", "3 g"),
                Pair("Karbohidrat", "8 g"),
                Pair("Serat", "0.4 g"),
                Pair("Natrium", "342 mg"),
            )
        ),
        Recipe(
            id = 3,
            image = R.drawable.pisang_panggang,
            name = "Pisang Panggang",
            tag = listOf(
                "Cemilan",
                "Pisang"
            ),
            portion = 4,
            age = ">9 Bulan",
            ingredients = "2 sisir pisang\n" +
                    "1 butir telur\n" +
                    "margarin\n",
            steps = "1. Potong pisang menjadi 8 bagian, lalu olesi dengan margarin dan diamkan selama 2 menit\n" +
                    "2. Kocok telur, lalu baluri pisang yang sudah didiamkan dengan telur\n" +
                    "3. Panggang pisang diatas teflon dengan api sedang selama 5 menit lalu sajikan\n",
            nutrition = listOf(
                Pair("Energi", "10 kkal"),
                Pair("Lemak", "0.3 g"),
                Pair("Protein", "0.3 g"),
                Pair("Karbohidrat", "1 g"),
                Pair("Serat", "0.1 g"),
                Pair("Natrium", "5 mg"),
            )
        ),
        Recipe(
            id = 4,
            image = R.drawable.banana_corn_puree,
            name = "Banana Corn Puree",
            tag = listOf(
                "Cemilan",
                "Pisang"
            ),
            portion = 1,
            age = "7-10 Bulan",
            ingredients = "1 sisir pisang\n" +
                    "1  buah jagung\n" +
                    "2 sdm oatmeal\n" +
                    "50 ml air\n",
            steps = "1. Pipil jagung dan kukus hingga matang\n" +
                    "2. Potong potong pisang\n" +
                    "3. Lalu blender semua bahan tunggu hingga halus lalu sajikan\n",
            nutrition = listOf(
                Pair("Energi", "292.3 kkal"),
                Pair("Lemak", "3.1 g"),
                Pair("Protein", "6.8 g"),
                Pair("Karbohidrat", "65.9 g"),
                Pair("Serat", "5.9 g"),
                Pair("Zat Besi", "2.2 mg"),
            )
        ),
        Recipe(
            id = 5,
            image = R.drawable.bubur_telur_santan,
            name = "Bubur Telur Santan",
            tag = listOf(
                "Menu Utama",
                "Bubur",
                "Telur"
            ),
            portion = 1,
            age = "7-10 Bulan",
            ingredients = "3 sdm nasi\n" +
                    "1 butir telur\n" +
                    "30 ml santan peras\n" +
                    "10 gr wortel (parut)\n" +
                    "200 ml air\n" +
                    "⅛ dst garam (0.1 gr) - bisa skip\n",
            steps = "1. Didihkan air di panci, masukan nasi masak hingga menjadi bubur, tambahkan wortel. Aduk rata.\n" +
                    "2. Tambahkan santan, dan garam aduk rata.\n" +
                    "3. Kemudian masukkan telur. Aduk rata.\n" +
                    "4. Ketika sudah matang, angkat dan saring.\n" +
                    "5. Sajikan\n",
            nutrition = listOf(
                Pair("Energi", "103 kkal"),
                Pair("Lemak", "5 g"),
                Pair("Protein", "4 g"),
                Pair("Karbohidrat", "11 g"),
                Pair("Serat", "0.3 g"),
                Pair("Zat Besi", "1.2 mg"),
                Pair("Zinc", "0.5 mg"),
                Pair("Natrium", "41 mg"),
                Pair("Kalsium", "45 mg")
            )
        ),
        Recipe(
            id = 6,
            image = R.drawable.bubur_daging_tempe_ayam,
            name = "Bubur Daging Tempe Bayam",
            tag = listOf(
                "Menu Utama",
                "Bubur",
                "Daging"
            ),
            portion = 2,
            age = "10-13 Bulan",
            ingredients = "3 sdm beras\n" +
                    "20 gr daging sapi cincang\n" +
                    "2 potong kecil tempe\n" +
                    "5 lembar daun bayam\n" +
                    "½ siung bawang putih\n" +
                    "Air secukupnya\n",
            steps = "1. Sangrai daging cincang di atas wajan sampai berubah warna dan matang. Sisihkan.\n" +
                    "2. Masak beras bersama air dalam panci sambil sesekali diaduk.\n" +
                    "3. Kalau sudah setengah matang, masukkan daging, tempe, dan bawang. Masak sampai menjadi bubur. Terakhir masukkan daun bayam.\n" +
                    "4. Blender bubur sampai halus. Saring dan sesuaikan dengan tekstur, sajikan selagi hangat.\n",
            nutrition = listOf(
                Pair("Energi", "315.9 kkal"),
                Pair("Lemak", "5.5 g"),
                Pair("Protein", "13.1 g"),
                Pair("Karbohidrat", "52.4 g"),
                Pair("Serat", "0.3 g"),
                Pair("Zat Besi", "1.4 mg"),
                Pair("Zinc", "1.9 mg")
            )
        ),
        Recipe(
            id = 7,
            image = R.drawable.pudding_telur,
            name = "Puding Telur",
            tag = listOf(
                "Cemilan",
                "Telur"
            ),
            portion = 12,
            age = "7-10 Bulan",
            ingredients = "1 butir telur ayam\n" +
                    "100 ml susu UHT\n" +
                    "1 sdm gula pasir\n" +
                    "Garam secukupnya\n",
            steps = "1. Kocok telur dalam wadah, tambahkan susu dan garam.\n" +
                    "2. Saring, kemudian pindahkan ke wadah tahan panas.\n" +
                    "3. Tutup dengan alumunium foil.\n" +
                    "4. Kukus selama 20 menit.\n" +
                    "5. Sajikan.\n",
            nutrition = listOf(
                Pair("Energi", "15 kkal"),
                Pair("Lemak", "0.8 g"),
                Pair("Protein", "0.8 g"),
                Pair("Karbohidrat", "1.2 g"),
                Pair("Zat Besi", "0.3 mg"),
                Pair("Zinc", "0.1 mg"),
                Pair("Natrium", "10 mg"),
                Pair("Kalsium", "16 mg")
            )
        ),
        Recipe(
            id = 8,
            image = R.drawable.kue_telur_dan_ubi_cilembu,
            name = "Kue Telur dan Ubi Cilembu",
            tag = listOf(
                "Cemilan",
                "Telur",
                "Ubi"
            ),
            portion = 10,
            age = "10-13 Bulan",
            ingredients = "1 buah cilembu ukuran sedang yang sudah dikukus\n" +
                    "1 butir telur\n" +
                    "50 ml santan\n" +
                    "Coklat atau meses (opsional)\n",
            steps = "1. Lumatkan ubi yang sudah dikukus sampai halus.\n" +
                    "2. Tambahkan 1 butir telur dan santan. Aduk sampai rata.\n" +
                    "3. Masukkan ke dalam wadah tahan panas.\n" +
                    "4. Taburi topping, bisa pakai coklat, mesis, atau almond slice ya AyBun.\n" +
                    "5. Kukus 25 menit dan siap disajikan.\n",
            nutrition = listOf(
                Pair("Energi", "33 kkal"),
                Pair("Lemak", "1 g"),
                Pair("Protein", "1 g"),
                Pair("Karbohidrat", "5 g"),
                Pair("Zat Besi", "0.2 mg"),
                Pair("Zinc", "0.1 mg"),
                Pair("Natrium", "8 mg"),
                Pair("Kalsium", "10 mg")
            )
        ),
        Recipe(
            id = 9,
            image = R.drawable.nasi_tim_tempe,
            name = "Nasi Tim Tempe",
            tag = listOf(
                "Menu Utama",
                "Tempe",
                "Nasi"
            ),
            portion = 1,
            age = ">10 Bulan",
            ingredients = "50 gram nasi aron (nasi yang sudah dimasak setengah matang dengan direbus sampai air berkurang)\n" +
                    "15 gram tempe, iris tipis\n" +
                    "20 gram labu siam, iris tipis \n" +
                    "10 gram tomat, buang kulitnya\n" +
                    "75 ml (1/3 gelas belimbing) santan encer\n" +
                    "Dapat ditambah daun bawang, seledri, bawang bombay secukupnya\n",
            steps = "1. Letakkan nasi aron dan tempe pada wadah tim\n" +
                    "2. Tambahkan santan encer dan bumbu (seledri, daun bawang, daun bombay), tim hingga matang\n" +
                    "3. Tambahkan labu siam dan tomat, tim hingga matang, angkat \n" +
                    "4. Sajikan \n",
            nutrition = listOf(
                Pair("Energi", "190 kkal"),
                Pair("Lemak", "9 g"),
                Pair("Protein", "5 g"),
                Pair("Karbohidrat", "22 g"),
                Pair("Zat Besi", "1.835 mg"),
                Pair("Zinc", "0.68 mg"),
                Pair("Natrium", "2.25 mg"),
                Pair("Kalsium", "41.4 mg")
            )
        ),
        Recipe(
            id = 10,
            image = R.drawable.bubur_sumsum_kacang_ijo,
            name = "Bubur Sumsum Kacang Hijau",
            tag = listOf("Menu Utama", "Bayam", "Kacang Hijau"),
            portion = 1,
            age = ">6 Bulan",
            ingredients = "15 gram (1,5 sdm) tepung beras\n" +
                    "10 gr (1 sdm) kacang hijau, rendam dengan air\n" +
                    "75 ml (1/3 gelas belimbing) santan encer\n" +
                    "20 gr daun bayam, iris halus",
            steps = "1. Rebus kacang hijau dalam air mendidih tunggu hingga setengah matang lalu masukkan bayam\n" +
                    "2. Tunggu hingga kacang hijau dan bayam matang sampai melunak\n" +
                    "3. Angkat dan tiriskan kemudian blender hingga halus, sisihkan\n" +
                    "4. Campurkan sedikit air hangat dengan tepung beras hingga larut di mangkuk berbeda\n" +
                    "5. Tambahkan campuran kacang hijau dan bayam yang telah halus dengan tepung beras, aduk rata\n" +
                    "6. Sajikan",
            nutrition = listOf(
                Pair("Energi", "149 kkal"),
                Pair("Lemak", "8 g"),
                Pair("Protein", "3 g"),
                Pair("Karbohidrat", "18 g"),
                Pair("Serat", "2.64 g"),
                Pair("Zat Besi", "1.37 mg"),
                Pair("Zinc", "0.68 mg"),
                Pair("Natrium", "2.25 mg"),
                Pair("Kalsium", "52.2 mg")
            )
        ),
        Recipe(
            id = 11,
            image = R.drawable.nugget_sayuran,
            name = "Nugget Sayuran",
            tag = listOf("Cemilan", "Ubi", "Bayam"),
            portion = 2,
            age = ">12 Bulan",
            ingredients = "150 gr ubi jalar merah rebus, haluskan\n" +
                    "50 gr (1 butir) telur ayam, kocok\n" +
                    "30 gr daging ayam\n" +
                    "1 cangkir bayam, cincang kasar\n" +
                    "20 gr (2 sdm) wortel, parut kasar\n" +
                    "3 siung bawang putih, cincang\n" +
                    "garam secukupnya\n" +
                    "minyak untuk menggoreng",
            steps = "1. Campur bayam, wortel, ubi, telur, daging ayam, bawang putih dan garam hingga rata\n" +
                    "2. Ambil 1 sendok makan bahan. Bentuk bulat pipih menggunakan tangan. Lakukan langkah serupa pada sisa bahan.\n" +
                    "3. Ambil 1 buah nugget, celupkan ke dalam kocokan telur. Masukkan pada wajan berisi minyak panas. Goreng nugget hingga berubah warna dan matang. Angkat dan tiriskan.\n" +
                    "4. Lakukan langkah serupa pada sisa bahan. Sajikan selagi hangat",
            nutrition = listOf(
                Pair("Energi", "270.5 kkal"),
                Pair("Lemak", "15.7 g"),
                Pair("Protein", "10.6 g"),
                Pair("Karbohidrat", "18.275 g"),
                Pair("Serat", "3 g"),
                Pair("Zat Besi", "1.53 mg"),
                Pair("Zinc", "0.74 mg"),
                Pair("Natrium", "95.4 mg"),
                Pair("Kalsium", "60.03 mg")
            )
        ),
        Recipe(
            id = 12,
            image = R.drawable.rolade_ayam,
            name = "Rolade Bayam",
            tag = listOf("Cemilan", "Bayam", "Ayam"),
            portion = 15,
            age = ">12 Bulan",
            ingredients = "250 gram daging ayam cincang\n" +
                    "60 gram bayam, rebus, potong halus\n" +
                    "50 gram wortel, kupas, cuci bersih, potong dadu kecil-kecil halus\n" +
                    "1 sdm tepung terigu\n" +
                    "1 butir telur ayam\n" +
                    "2 butir telur ayam, kocok lepas, untuk membuat kulit dadar\n" +
                    "2 siung bawang putih, haluskan\n" +
                    "1 sdm bawang bombay, cincang halus",
            steps = "1. Campur daging ayam cincang dengan tepung terigu, wortel, 1 butir telur, bawang putih, dan bawang bombay, lalu aduk sampai rata\n" +
                    "2. Siapkan 1 lembar telur dadar tipis, masukkan adonan daging ayam, ratakan dan tutup dengan potongan daun bayam rebus, kemudian gulung, padatkan bentuknya\n" +
                    "3. Kukus rolade bayam selama 20 menit sampai matang\n" +
                    "4. Angkat dan potong-potong rolade bayam, lalu sajikan",
            nutrition = listOf(
                Pair("Energi", "69 kkal"),
                Pair("Lemak", "4 gr"),
                Pair("Protein", "6 gr"),
                Pair("Karbohidrat", "0.9 gr"),
                Pair("Serat", "0.248 g"),
                Pair("Zat Besi", "0.334 mg"),
                Pair("Zinc", "0.317 mg"),
                Pair("Natrium", "48.219 mg"),
                Pair("Kalsium", "13.795 mg")
            )
        )
    )
}