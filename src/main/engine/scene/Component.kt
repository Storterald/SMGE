package scene

abstract class Component

abstract class CustomScript(val startCode: () -> Unit, val updateCode: () -> Unit): Component()