import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:pin_code_fields/pin_code_fields.dart';

import '../mes_constantes.dart';
import 'change_password.dart';

class ForgetDigitCode extends StatefulWidget {
  const ForgetDigitCode({super.key});

  @override
  State<ForgetDigitCode> createState() => _ForgetDigitCodeState();
}

class _ForgetDigitCodeState extends State<ForgetDigitCode> {
  final _formKey = GlobalKey<FormState>();
  String _enteredCode = '';

  void _submitCode() {
    if (_enteredCode.length == 3) {
      Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => ChangePassword()),
      );
    } else {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Veuillez entrer un code à 3 chiffres')),
      );
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: EdgeInsets.only(top: 50, right: 20, left: 20),
        child: Form(
          key: _formKey,
          child: ListView(
            children: [
              Text(
                "Réinitialisation mot de passe",
                style: GoogleFonts.poppins(
                  fontSize: 20,
                  color: Colors.black,
                  fontWeight: FontWeight.w600,
                ),
              ),
              SizedBox(height: 50),
              Text(
                "Veuillez entrer le code de réinitialisation que vous avez reçu",
                textAlign: TextAlign.center,
                style: GoogleFonts.poppins(
                  fontSize: 18,
                  color: Colors.grey.shade700,
                  fontWeight: FontWeight.w400,
                ),
              ),
              SizedBox(height: 50),
              PinCodeTextField(
                appContext: context,
                length: 3,
                keyboardType: TextInputType.number,
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                pinTheme: PinTheme(
                  shape: PinCodeFieldShape.box,
                  borderRadius: BorderRadius.circular(5),
                  fieldHeight: 60,
                  fieldWidth: 60,
                  activeColor: Colors.grey,
                  selectedColor: Colors.grey,
                  inactiveColor: Colors.grey,
                ),
                onChanged: (value) {
                  _enteredCode = value;
                },
              ),
              SizedBox(height: 70),
              InkWell(
                onTap: _submitCode,
                child: Container(
                  height: 50,
                  width: double.infinity,
                  decoration: BoxDecoration(
                    color: secondaryColor,
                    borderRadius: BorderRadius.circular(8),
                  ),
                  child: Center(
                    child: Text(
                      "Vérifier",
                      style: GoogleFonts.poppins(
                        color: Colors.white,
                        fontWeight: FontWeight.w500,
                        fontSize: 18,
                      ),
                    ),
                  ),
                ),
              ),
              SizedBox(height: 30),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "Code non reçu?",
                    style: GoogleFonts.poppins(
                      color: Colors.grey,
                      fontWeight: FontWeight.w500,
                      fontSize: 16,
                    ),
                  ),
                  SizedBox(width: 10),
                  InkWell(
                    onTap: () {
                      // Resend code logic
                    },
                    child: Text(
                      "Renvoyer",
                      style: GoogleFonts.poppins(
                        color: secondaryColor,
                        fontWeight: FontWeight.w500,
                        fontSize: 16,
                      ),
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
