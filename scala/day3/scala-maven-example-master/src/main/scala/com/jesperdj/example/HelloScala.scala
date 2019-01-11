package com.jesperdj.example

import akka.actor.{Actor, ActorSystem, Props}

object HelloScala extends App {
  val system = ActorSystem("HelloSystem")
  // default Actor constructor
  val helloActor = system.actorOf(Props[HelloActor], name = "helloactor")
  helloActor ! "hello"
  helloActor ! "buenos dias"
}

class HelloActor extends Actor {
  def receive = {
    case "hello" => println("hello back at you")
    case _       => println("huh?")
  }
}

class Kid extends Actor {
  def act(): Unit = {
        case Poke => {
          println("Ow...") println ("Quit it...")
        }
        case Feed => {
          println("Gurgle...")
          println("Burp...")
        }
      }
    }
  }
}
