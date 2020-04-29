# DotLoadingView

https://www.youtube.com/watch?v=jdaVSCMgvKo&feature=youtu.be

<img src="https://user-images.githubusercontent.com/18132015/80567391-438aa380-8a1f-11ea-9ddd-4076080455d0.jpg" width="300"/>

# How to

To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

    gradle
    maven
    sbt
    leiningen

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.nguyenkhiem7789:DotLoadingView:0.1.0'
	}

# How to use it?

To use as a View:

	<com.nguyen.dotloading.DotLoadingView
		android:layout_width="match_parent"
		android:layout_height="0dp"
		android:layout_weight="1"
		app:dot_color="@color/blue"
		app:dot_distance="20dp"
		app:dot_min_radius="4dp"
		app:dot_max_radius="10dp"
		app:dot_count="4"
		/>
		
To show Loading Dialog: 

	DotLoadingDialog.show(this)
	Handler().postDelayed(Runnable {
		DotLoadingDialog.dimiss()
	}, 3000)
	
