import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../mes_constantes.dart';
import '../page_accueil/home.dart'; // Assurez-vous que 'secondaryColor' est défini dans ce fichier

class Preferences extends StatefulWidget {
  const Preferences({super.key});

  @override
  State<Preferences> createState() => _PreferencesState();
}

class _PreferencesState extends State<Preferences> {
  final List<Map<String, String>> preferer = [
    {
      "name": "Halal",
      "icon": "assets/icons/halal.png",
    },
    {
      "name": "Végétarien",
      "icon": "assets/icons/vegetarien.png",
    },
    {
      "name": "Non végétarien",
      "icon": "assets/icons/non_vegetarien.png",
    },
    {
      "name": "Végan",
      "icon": "assets/icons/vegan.png",
    },
  ];

  List<bool> _selectionStatus = List.generate(
      4, (_) => false); // Correspondre à la longueur de 'allergiques'
  bool _isEditing = false;
  TextEditingController _prefererController = TextEditingController();

  void _toggleSelection(int index) {
    setState(() {
      _selectionStatus[index] = !_selectionStatus[index];
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Container(
        padding: EdgeInsets.only(bottom: 30, top: 30, right: 20, left: 20),
        child: ListView(
          children: [
            Center(
              child: Text(
                "Préférences alimentaires",
                style: GoogleFonts.poppins(
                    fontSize: 20,
                    color: Colors.black,
                    fontWeight: FontWeight.w600),
              ),
            ),
            SizedBox(height: 30),
            Text(
              "Avez-vous des préférences alimentaires ?\nSi oui, veuillez sélectionner une case.",
              textAlign: TextAlign.center,
              style: GoogleFonts.poppins(
                fontSize: 14,
                color: Colors.grey.shade700,
                fontWeight: FontWeight.w400,
              ),
            ),
            SizedBox(height: 30),
            // Liste des boutons personnalisés
            Column(
              children: preferer.asMap().entries.map((entry) {
                int index = entry.key;
                Map<String, String> prefer = entry.value;
                return GestureDetector(
                  onTap: () => _toggleSelection(index),
                  child: Container(
                    padding: EdgeInsets.all(10),
                    //height: 70,
                    width: double.infinity,
                    margin: EdgeInsets.only(bottom: 15),
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(20),
                      border: Border.all(
                        color: Colors.grey,
                      ),
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Container(
                                padding: EdgeInsets.only(left: 5),
                                alignment: Alignment.topLeft,
                                child: Image.asset(prefer["icon"]!, height: 30)),
                            SizedBox(width: 10),
                            Text(
                              prefer["name"]!,
                              style: GoogleFonts.poppins(
                                fontSize: 18,
                                color: Colors.black,
                                fontWeight: FontWeight.w400,
                              ),
                            ),
                          ],
                        ),
                        // Indicateur de sélection
                        Container(
                          width: 20,
                          height: 20,
                          decoration: BoxDecoration(
                            shape: BoxShape.circle,
                            color: _selectionStatus[index]
                                ? secondaryColor
                                : Colors.transparent,
                            border: Border.all(color: Colors.grey),
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              }).toList(),
            ),

            //SizedBox(height: 30),
            // Container transformable en TextField
            Container(
              // height: 70,
              width: double.infinity,
              padding: EdgeInsets.all(10),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(20),
                  border: Border.all(color: Colors.grey)),
              child: _isEditing
                  ? Center(
                child: TextField(
                  controller: _prefererController,
                  decoration: InputDecoration(
                    hintText: 'Veuillez entrez votre préférences alimentaire',
                    border: InputBorder.none,
                    hintStyle:
                    TextStyle(color: Colors.grey, fontSize: 18),
                    suffixIcon: IconButton(
                      onPressed: () {
                        setState(() {
                          _isEditing = false;
                        });
                      },
                      icon: Icon(
                        Icons.arrow_drop_up,
                        color: secondaryColor,
                        size: 40,
                      ),
                    ),
                  ),
                ),
              )
                  : Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Container(padding: EdgeInsets.only(left: 5),child: Image.asset("assets/icons/dot.png", height: 30)),
                      Text(
                        "Autres",
                        style: GoogleFonts.poppins(
                          fontSize: 16,
                          color: Colors.black,
                          fontWeight: FontWeight.w400,
                        ),
                      ),
                    ],
                  ),
                  IconButton(
                    onPressed: () {
                      setState(() {
                        _isEditing = true;
                      });
                    },
                    icon: Icon(
                      Icons.arrow_drop_down,
                      color: secondaryColor,
                      size: 40,
                    ),
                  ),
                ],
              ),
            ),
            SizedBox(height: 70),
            InkWell(
              onTap: () async {
                final prefs = await SharedPreferences.getInstance();
                await prefs.setBool('isLoggedIn', true);
                List<String> selectedMaladies = [];
                for (int i = 0; i < _selectionStatus.length; i++) {
                  if (_selectionStatus[i]) {
                    selectedMaladies.add(preferer[i]["name"]!);
                  }
                }

                String autreMaladie = _prefererController.text.trim();
                if (autreMaladie.isNotEmpty) {
                  selectedMaladies.add(autreMaladie);
                }
                print("je préfére : $selectedMaladies");
                Navigator.pushReplacement(context, MaterialPageRoute(builder: (context)=>Home()));

              },
              child: Container(
                height: 50,
                width: double.infinity,
                decoration: BoxDecoration(
                    color: secondaryColor,
                    borderRadius: BorderRadius.circular(20)),
                child: Center(
                  child: Text(
                    "Démarrer",
                    style: GoogleFonts.poppins(
                        color: Colors.white,
                        fontWeight: FontWeight.w500,
                        fontSize: 15),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
