import 'package:flutter/material.dart';

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Écran d\'accueil'),
      ),
      body: Center(
        child: Text(
          'Contenu de l\'écran d\'accueil',
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}
