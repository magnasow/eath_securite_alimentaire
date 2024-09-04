// lib/services/api_service.dart
import 'package:http/http.dart' as http;
import 'dart:convert';

class ApiService {
  final String baseUrl;

  ApiService(this.baseUrl);

  Future<String> fetchData() async {
    final response = await http.get(Uri.parse('$baseUrl/api/auth/register'));

    if (response.statusCode == 200) {
      var data = jsonDecode(response.body);
      return data.toString(); // Assurez-vous de convertir les données en une chaîne si nécessaire
    } else {
      throw Exception('Failed to load data');
    }
  }

  Future<String> postData() async {
    final response = await http.post(
      Uri.parse('$baseUrl/api/auth/register'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, String>{
        'key': 'value', // Remplacez par les données réelles que vous souhaitez envoyer
      }),
    );

    if (response.statusCode == 201) {
      var data = jsonDecode(response.body);
      return data.toString(); // Assurez-vous de convertir les données en une chaîne si nécessaire
    } else {
      throw Exception('Failed to post data');
    }
  }
}
