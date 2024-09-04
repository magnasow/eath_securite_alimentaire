import 'package:eath/connexion_inscription/succes.dart';
import 'package:eath/mes_constantes.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

class ChangePassword extends StatefulWidget {
  const ChangePassword({super.key});

  @override
  State<ChangePassword> createState() => _ChangePasswordState();
}

class _ChangePasswordState extends State<ChangePassword> {
  final _formKey = GlobalKey<FormState>();
  String _newPassword = '';
  String _confirmPassword = '';

  void _submitForm() {
    if (_formKey.currentState!.validate()) {
      // Passwords match, proceed to the success page
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => Succes()),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: EdgeInsets.only(bottom: 20, top: 70, right: 20, left: 20),
        child: Stack(
          children: [
            Column(
              children: [
                Center(
                  child: Text(
                    "Nouveau mot de passe",
                    style: GoogleFonts.poppins(
                      fontSize: 20,
                      color: Colors.black,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                ),
                SizedBox(height: 50),
                Text(
                  "Veuillez entrer le nouveau mot de passe",
                  textAlign: TextAlign.center,
                  style: GoogleFonts.poppins(
                    fontSize: 15,
                    color: Colors.grey.shade700,
                    fontWeight: FontWeight.w400,
                  ),
                ),
                SizedBox(height: 50),
                Form(
                  key: _formKey,
                  child: Column(
                    children: [
                      TextFormField(
                        obscureText: true,
                        decoration: InputDecoration(
                          label: Text(
                            "Nouveau mot de passe",
                            style: GoogleFonts.poppins(
                              color: Colors.grey,
                              fontSize: 15,
                                fontWeight: FontWeight.w500
                            ),
                          ),
                        ),
                        onChanged: (value) {
                          _newPassword = value;
                        },
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Veuillez entrer un nouveau mot de passe';
                          }
                          if (value.length < 6) {
                            return 'Le mot de passe doit contenir au moins 6 caractères';
                          }
                          return null;
                        },
                      ),
                      SizedBox(height: 40),
                      TextFormField(
                        obscureText: true,
                        decoration: InputDecoration(
                          label: Text(
                            "Confirmer mot de passe",
                            style: GoogleFonts.poppins(
                              color: Colors.grey,
                              fontSize: 15,
                                fontWeight: FontWeight.w500
                            ),
                          ),
                        ),
                        onChanged: (value) {
                          _confirmPassword = value;
                        },
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Veuillez confirmer votre mot de passe';
                          }
                          if (value != _newPassword) {
                            return 'Les mots de passe ne correspondent pas';
                          }
                          return null;
                        },
                      ),
                    ],
                  ),
                ),
              ],
            ),
            Align(
              alignment: Alignment.bottomCenter,
              child: InkWell(
                onTap: _submitForm,
                child: Container(
                  height: 50,
                  width: double.infinity,
                  decoration: BoxDecoration(
                    color: secondaryColor,
                    borderRadius: BorderRadius.circular(8),
                  ),
                  child: Center(
                    child: Text(
                      "Confirmer",
                      style: GoogleFonts.poppins(
                        color: Colors.white,
                        fontWeight: FontWeight.w500,
                        fontSize: 18,
                      ),
                    ),
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
