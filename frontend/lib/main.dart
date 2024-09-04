import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'splash_screen.dart'; // Assurez-vous que ce fichier existe
import 'home_screen.dart';  // Assurez-vous que ce fichier existe
import 'widgets/example_widget.dart'; // Assurez-vous que le chemin est correct

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  SystemChrome.setPreferredOrientations([
    DeviceOrientation.portraitUp,
    DeviceOrientation.portraitDown
  ]).then((_) {
    runApp(Eath());
  });
}

class Eath extends StatelessWidget {
  const Eath({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      initialRoute: '/',
      routes: {
        '/': (context) => const SplashScreen(),
        '/home': (context) => const HomeScreen(),
        // Ajoutez d'autres routes ici
      },
    );
  }
}
