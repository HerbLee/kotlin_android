# kotlin_android


	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}

  

	dependencies {
		implementation 'com.github.HerbLee:kotlin_android:v1.0.6'
	}
	
	api "com.squareup.okhttp3:okhttp:3.7.0"
	api "com.squareup.okio:okio:1.12.0"

	    api 'com.orhanobut:logger:2.2.0'
	    api 'com.android.support:recyclerview-v7:27.1.1'
	    api 'com.squareup.picasso:picasso:2.5.2'
	    api group: 'org.json', name: 'json', version: '20180813'
	    api group: 'com.fasterxml.jackson.core', name: 'jackson-core', version: '2.9.9'
	    api group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.9.9'
	    api group: 'com.fasterxml.jackson.core', name: 'jackson-annotations', version: '2.9.9'
	
	

