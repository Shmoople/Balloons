# Gradle projects are super helpful when working with java modules

Look, all you have to do is make sure that the workspace contains a "gradlew" or "settings.gradle" file. Then its completely fair game from that point forward

I'm literally this close to offing myself from stress and anxiety and that is okay because sometimes its okay not to be okay. And that is just totally fine!

I can't wait for this move movie to come out at some time.. I don't know dude.

```Java

@Override
public void start(Stage primaryStage) {

    primaryStage.setTitle("Baloons"); // make that a final static thingamabob
    
    Group root = new Group();

    HashSet<Location> hs = HashSet<Location>(); // don't use locations they suck
    Graph<Location> g = new Graph<Location>(hs, null); // you can initialize

    // sometimes I just don't know what the fuck is going on and that's totally fine because there are some things in life that are totally never certain or perfect and you just have to live with that shit (this comment is way to long I should put it on a multi line comment)

    // Am I good at this? Or is it something else? I don't know dude that just doesn't make sense and I'm shaking from a complete absence of stuff going on that happens for other stuff and that is how it works for the most part and I'm totally stressed I can't stop and focus on the task at hand instead I'm just writing out my thoughts on this fucking document that doesn't need to be written out what the fuck happened to my english class I really hope I don't fail it I won't fail it why would that happen? I don't know anymore I missed both my classes yesterday and I'm stressed fuck fuck 
}
```

ok so fun fact you can't actually generate javadoc through gradle if there are any characters using incorrect encoding, soooooo remove all of those if you wanna have javadoc generation...



in order to transfer this fucking spaghetti sausage of a project to a gradle configuration... a couple steps need to be made


1) refactor, refactor everything. Make sure that all names are fully qualified and every fucking method has a javadoc attachment
2) Reorganize program structure, astar shouldn't be in a package
3) Don't utilize non-UTF8 characters for this project
4) Rewrite entity files
please use fully qualified names, so far the github repository is in working condition along with everything else

gradle build is functiona!!! LETS GOOOOOOOOO

