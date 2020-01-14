# Tracker_v2

Tracker_v2 is the new and improved version of the [Tracker App](https://github.com/LexuBBAA/Tracker). The new version of the app (the _v2_) will support a private web service written in Java using Spring Boot, a native Android app developed in Kotlin, and also web & iOS versions so that the users can benefit from the performance boosts on their preferred platform.

### Repository structure

During the development phase, the various features will be delivered under the following structure on this repo:

```
Tracker_v2
	- Tracker_v2_ws > The features & services supported by the Tracker web services
	- Tracker_v2_android > The Tracker Android application
	- Tracker_v2_iOS > The Tracker iOS application
	- Tracker_v2_web > The Tracker Web application & admin pannel
```

### Running an individual service

Starting the services is easy; the services have been developed to be packaged into executable JARs, so you can use the commands below to manage the ecosystem:

**Start a service:**
```
// To start a service, first you must navigate to the module's directory:
cd path-to-project/ws/tracker_auth/

// Now you can just type the below command, and the Auth WS will start running:
./start_ws
```

**Stop a service:** (the recommended way)
```
// To stop a running service, navigate to the module's directory:
cd path-to-project/ws/tracker_auth/

// Now you only need to type the following command:
./stop_ws
```

**Start & stop all services:**
```
// Navigate to the project directory:
cd path-to-project/ws

// To start all Tracker services, use the command below:
./start

// To stop all Tracker services, use the below command:
./stop
```


### Contact
**Thank you for your interest in this project**
If you have any questions, feedback, or want to contribute, you can contact us at:
> **Facebook:** *Bogdan Andrei Alexandru*
> **Email:** *a.birsasteanu@gmail.com*


## Authors
* *Android Developer* - **Bogdan Birsasteanu** - [LexuBBAA](https://github.com/LexuBBAA/)
* *Back-end Developers* - **Bogdan Birsasteanu** - [LexuBBAA](https://github.com/LexuBBAA/), **Mihai Godza** - [mgodza93](https://github.com/mgodza93)
* *Front-end Developers* - **Bogdan Birsasteanu** - [LexuBBAA](https://github.com/LexuBBAA/), **Mihai Godza** - [mgodza93](https://github.com/mgodza93)
* *iOS Developer* - **Bogdan Birsasteanu** - [LexuBBAA](https://github.com/LexuBBAA/)
* *UI/UX Designers* - **Bogdan Birsasteanu** - [LexuBBAA](https://github.com/LexuBBAA/), **Mihai Godza** - [mgodza93](https://github.com/mgodza93)

## License

This project is licensed under the following license: N/A.
