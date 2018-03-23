# JPF-3D-State-Space

A JPF listener that allows the user to view and analyze their application’s state space in a 3D environment coded in Java using LWJGL libraries (OpenGL).

## Setup using Eclipse

1. Install [Eclipse IDE for Java EE Developers](https://www.eclipse.org/downloads/packages/).

2. Install [Eclipse JPF plugin](https://babelfish.arc.nasa.gov/trac/jpf/wiki/install/eclipse-plugin) to your Eclipse IDE. 

3. Under the Git Repositories view in Eclipse, select "Clone a Git repository. Enter this repository's URL under the URI field and press Next.

4. Right click "JPF-3D-State-Space [master]" and select "Import Projects..". Only select JPF-3D-State-Space\jpf-3d-state-space and press Finish.

5. Change the value of "path" below to the absolute path of [jpf-core\build\jpf.jar](https://babelfish.arc.nasa.gov/trac/jpf/wiki/install/start) in your system found in [JPF-3D-State-Space\jpf-3d-state-space\.classpath](jpf-3d-state-space/.classpath). Any dependency errors should be resolved.

That is, 

```
<classpathentry kind="lib" path="<ABSOLUTE_PATH_HERE>/jpf-core/build/jpf.jar" sourcepath=""/>
```

## How to Run using Eclipse

Under [JPF-3D-State-Space\jpf-3d-state-space\src\examples](jpf-3d-state-space/src/examples/), right click any .jpf file, and press "Verify...".

Alternatively, you can create your own .jpf file with your application and run it with the ```StateSpace3D``` listener.

## Credit

This project was made for [EECS4315, a mission-critical systems class at York University.](https://wiki.eecs.yorku.ca/course_archive/2017-18/W/4315/syllabus)

Created by:
- Kevin Arindaeng
- Yash Chauhan