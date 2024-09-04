// lib/widgets/example_widget.dart
import 'package:flutter/material.dart';
import '../services/api_service.dart'; // Assurez-vous que ce chemin est correct

class ExampleWidget extends StatefulWidget {
  @override
  _ExampleWidgetState createState() => _ExampleWidgetState();
}

class _ExampleWidgetState extends State<ExampleWidget> {
  late ApiService apiService;
  String responseData = '';

  @override
  void initState() {
    super.initState();
    apiService = ApiService('http://localhost:8081'); // Utilisez l'URL appropriée
  }

  Future<void> _fetchData() async {
    try {
      final data = await apiService.fetchData();
      setState(() {
        responseData = data;
      });
    } catch (e) {
      print('Error fetching data: $e');
    }
  }

  Future<void> _postData() async {
    try {
      final data = await apiService.postData();
      setState(() {
        responseData = data;
      });
    } catch (e) {
      print('Error posting data: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Example Widget')),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            ElevatedButton(
              onPressed: _fetchData,
              child: Text('Fetch Data'),
            ),
            ElevatedButton(
              onPressed: _postData,
              child: Text('Post Data'),
            ),
            if (responseData.isNotEmpty) ...[
              SizedBox(height: 20),
              Text('Response: $responseData'),
            ],
          ],
        ),
      ),
    );
  }
}
