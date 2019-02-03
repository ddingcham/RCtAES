# [RCtAES](https://martinfowler.com/articles/refactoring-external-service.html#musings)
#### Refactoring code that accesses external services _ Martin Fowler  
```
When I write code that deals with external services, 
I find it valuable to separate that access code into separate objects.
```
#### Here I show how I would refactor some congealed code into a common pattern of this separation.

#### In order to something useful, they usually need to talk to other bits of software, writeen by different people ...

kind of external collaboration, I think it's  
#### particularly useful to apply good modularity and encapsulation.

In this article I'll take a simple example, and walk through the refactorings
#### that introduce the kind of modularity I'm looking for.

출처 : [Refactoring code that accesses external services _ Martin Fowler](https://martinfowler.com/articles/refactoring-external-service.html#musings)  

