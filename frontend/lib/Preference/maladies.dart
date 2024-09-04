import 'package:eath/Preference/allergies.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

import '../mes_constantes.dart';

class Maladies extends StatefulWidget {
  const Maladies({super.key});

  @override
  State<Maladies> createState() => _MaladiesState();
}

class _MaladiesState extends State<Maladies> {
  List<String> maladies = [
    'Diabète',
    'Enceinte',
    'Hypertension artérielle',
    "Hépatite",
    "Anorexie"
  ]; // Liste des maladies
  List<bool> _selectionStatus = [
    false,
    false,
    false,
    false,
    false
  ]; // État de chaque bouton
  bool _nonSelected = false; // État du bouton "Non"
  bool _isEditing = false;
  TextEditingController _autreMaladieController =
      TextEditingController(); // Champ pour une autre maladie

  // Méthode pour changer l'état d'un bouton
  void _toggleSelection(int index) {
    setState(() {
      if (_nonSelected) {
        _nonSelected = false; // Si "Non" est sélectionné, le désélectionner
      }
      _selectionStatus[index] = !_selectionStatus[index]; // Inverser l'état
    });
  }

  // Méthode pour gérer la sélection de "Non"
  void _toggleNonSelection() {
    setState(() {
      _nonSelected = !_nonSelected; // Inverser l'état de "Non"
      if (_nonSelected) {
        // Si "Non" est sélectionné, désélectionner toutes les autres maladies
        _selectionStatus.fillRange(0, _selectionStatus.length, false);
      }
    });
  }

  // Méthode pour gérer l'affichage du TextField
  void _toggleEditMode() {
    setState(() {
      _isEditing = !_isEditing; // Inverser l'état d'édition
      if (!_isEditing) {
        _autreMaladieController.text =
            ''; // Réinitialiser le champ de texte lorsqu'on quitte le mode d'édition
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Container(
        padding: EdgeInsets.only(bottom: 30, top: 50, right: 20, left: 20),
        child: ListView(
          children: [
            Center(
              child: Text(
                "Maladies",
                style: GoogleFonts.poppins(
                    fontSize: 20,
                    color: Colors.black,
                    fontWeight: FontWeight.w600),
              ),
            ),
            SizedBox(height: 30),
            Text(
              "Avez-vous des antécédents médicaux ?\nSi oui, veuillez sélectionner une case.",
              textAlign: TextAlign.center,
              style: GoogleFonts.poppins(
                fontSize: 16,
                color: Colors.grey.shade700,
                fontWeight: FontWeight.w400,
              ),
            ),
            SizedBox(height: 30),
            // Liste des boutons personnalisés
            Column(
              children: List.generate(maladies.length, (index) {
                return GestureDetector(
                  onTap: () => _toggleSelection(index),
                  child: Container(
                    padding: EdgeInsets.all(10),
                    height: 50,
                    width: double.infinity,
                    margin: EdgeInsets.only(bottom: 30),
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
                        Text(
                          maladies[index],
                          style: GoogleFonts.poppins(
                            fontSize: 16,
                            color: Colors.black,
                            fontWeight: FontWeight.w400,
                          ),
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
              }),
            ),
            // Bouton "Non"
            GestureDetector(
              onTap: _toggleNonSelection,
              child: Container(
                padding: EdgeInsets.all(10),
                height: 50,
                width: double.infinity,
                margin: EdgeInsets.symmetric(vertical: 5),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(20),
                  border: Border.all(
                    color: _nonSelected ? Colors.blue : Colors.grey,
                  ),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      "Non",
                      style: GoogleFonts.poppins(
                        fontSize: 16,
                        color: Colors.black,
                        fontWeight: FontWeight.w400,
                      ),
                    ),
                    // Indicateur de sélection
                    Container(
                      width: 20,
                      height: 20,
                      decoration: BoxDecoration(
                        shape: BoxShape.circle,
                        color: _nonSelected ? secondaryColor : Colors.transparent,
                        border: Border.all(color: Colors.grey),
                      ),
                    ),
                  ],
                ),
              ),
            ),
            SizedBox(height: 30),
            // Container transformable en TextField
            Container(
              height: 50,
              width: double.infinity,
              padding: EdgeInsets.only(right: 10, left: 10),
              decoration: BoxDecoration(
                  borderRadius: BorderRadius.circular(20),
                  border: Border.all(color: Colors.grey)),
              child: _isEditing
                  ? TextField(
                      controller: _autreMaladieController,
                      decoration: InputDecoration(
                          hintText: 'Veuillez entrez votre maladie',
                          border: InputBorder.none,
                          hintStyle: TextStyle(
                            color: Colors.grey,
                            fontSize: 18
                          ),
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
                              ))),
                    )
                  : Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          "Autres",
                          style: GoogleFonts.poppins(
                            fontSize: 16,
                            color: Colors.black,
                            fontWeight: FontWeight.w400,
                          ),
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
                            ))
                      ],
                    ),
            ),
            SizedBox(height: 30),
            InkWell(
              onTap: () {
                print(_autreMaladieController);
                List<String> selectedMaladies = [];
                for (int i = 0; i < _selectionStatus.length; i++) {
                  if (_selectionStatus[i]) {
                    selectedMaladies.add(maladies[i]);
                  }
                }

                String autreMaladie = _autreMaladieController.text.trim();
                if (autreMaladie.isNotEmpty) {
                  selectedMaladies.add(autreMaladie);
                }
                Navigator.pushReplacement(context, MaterialPageRoute(builder: (context)=>Allergies()));
                print("Maladies sélectionnées: $selectedMaladies");
              },
              child: Container(
                height: 50,
                width: double.infinity,
                decoration: BoxDecoration(
                    color: secondaryColor,
                    borderRadius: BorderRadius.circular(20)),
                child: Center(
                  child: Text(
                    "Suivant",
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
