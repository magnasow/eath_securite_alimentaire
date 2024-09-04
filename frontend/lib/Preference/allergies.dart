import 'package:eath/Preference/preferences.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

import '../mes_constantes.dart'; // Assurez-vous que 'secondaryColor' est défini dans ce fichier

class Allergies extends StatefulWidget {
  const Allergies({super.key});

  @override
  State<Allergies> createState() => _AllergiesState();
}

class _AllergiesState extends State<Allergies> {
  final List<Map<String, String>> allergiques = [
    {
      "name": "Poisson",
      "icon": "assets/icons/poisson.png",
    },
    {
      "name": "Oeuf",
      "icon": "assets/icons/oeuf.png",
    },
    {
      "name": "Lait",
      "icon": "assets/icons/lait.png",
    },
    {
      "name": "Arachide",
      "icon": "assets/icons/arachide.png",
    },
    {
      "name": "Fruit de mer",
      "icon": "assets/icons/fruit_de_mer.png",
    },
    {
      "name": "Lactose",
      "icon": "assets/icons/lactose.png",
    },
  ];

  List<bool> _selectionStatus = List.generate(
      6, (_) => false); // Correspondre à la longueur de 'allergiques'
  bool _isEditing = false;
  TextEditingController _allergieController = TextEditingController();

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
                "Allergies alimentaires",
                style: GoogleFonts.poppins(
                    fontSize: 20,
                    color: Colors.black,
                    fontWeight: FontWeight.w600),
              ),
            ),
            SizedBox(height: 30),
            Text(
              "Avez-vous des aliments dont vous êtes allergiques ?\nSi oui, veuillez sélectionner une case.",
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
              children: allergiques.asMap().entries.map((entry) {
                int index = entry.key;
                Map<String, String> allergiq = entry.value;
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
                                child: Image.asset(allergiq["icon"]!, height: 30)),
                            SizedBox(width: 10),
                            Text(
                              allergiq["name"]!,
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
                  controller: _allergieController,
                  decoration: InputDecoration(
                    hintText: 'Veuillez entrez votre allergie',
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
            SizedBox(height: 30),
            InkWell(
              onTap: () {
                List<String> selectedMaladies = [];
                for (int i = 0; i < _selectionStatus.length; i++) {
                  if (_selectionStatus[i]) {
                    selectedMaladies.add(allergiques[i]["name"]!);
                  }
                }

                String autreMaladie = _allergieController.text.trim();
                if (autreMaladie.isNotEmpty) {
                  selectedMaladies.add(autreMaladie);
                }
                print("Allergies: $selectedMaladies");
                Navigator.pushReplacement(context, MaterialPageRoute(builder: (context)=>Preferences()));

              },
              child: Container(
                height: 50,
                width: double.infinity,
                decoration: BoxDecoration(
                    color: secondaryColor,
                    borderRadius: BorderRadius.circular(20)),
                child: Center(
                  child: Text(
                    "Sauvegarder",
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
