import 'package:eath/connexion_inscription/connexion.dart';
import 'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';

import '../mes_constantes.dart';

class Succes extends StatefulWidget {
  const Succes({super.key});

  @override
  State<Succes> createState() => _SuccesState();
}

class _SuccesState extends State<Succes> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
      //  height: double.infinity,
        padding: EdgeInsets.only(right: 20, left: 20),
        child: ListView(
          children: [
            SizedBox(height: 70,),
            Center(child: Image.asset("assets/icons/succes.png", height: 150,)),
            SizedBox(height: 30,),
            Center(child: Text("Mot de passe changé!", style: GoogleFonts.poppins(fontSize: 20, color: Colors.black, fontWeight: FontWeight.w600),)),
            SizedBox(height: 20,),
            Center(child: Text("Votre mot de passe a été chaangé avec succès!",textAlign: TextAlign.center, style: GoogleFonts.poppins(fontSize: 18, color: Colors.grey.shade700, fontWeight: FontWeight.w400),)),
            SizedBox(height: 100,),
            InkWell(
              onTap: (){
                Navigator.push(context, MaterialPageRoute(builder: (context)=>Connexion()));
              },
              child: Container(
                height: 50,
                width: double.infinity,
                decoration: BoxDecoration(
                    color: secondaryColor,
                    borderRadius: BorderRadius.circular(8)
                ),
                child: Center(
                  child: Text("Se connecter", style: GoogleFonts.poppins(color: Colors.white, fontWeight: FontWeight.w500, fontSize: 18),),

                ),
              ),
            ),

          ],
        ),
      )
    );
  }
}
