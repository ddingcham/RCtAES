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

### 자바로 재구성해보기

스프링 배치의 JobLauncherTestUtils & Mockito 조합을 활용하면서 느낀 점
1-1. 위 조합(JobLauncherTestUtils & Mockito)을 이용해서 테스트하면서 스텝/잡을 만들어 봣음
1-2. 전체 플로우를 다 점검하지 않아도 되서 좋긴함
2. 그리고 리팩토링을 해봣음
3-1. 근데 결국에는 그 과정에서 나온 논리적인 규칙들이 pojo 흉내내는 객체들로 재작성 하게 됫고
3-2. 결국 위 조합을 사용하는 테스트 코드는 다 쓸모가 업서짐
3-3. 대신 내가 작성한 pojo기반 객체들에 대한 테스트 코드가 탄생함
4. 근데 처음부터 접근방식을 위 조합이 아닌 다른 접근 방식을 하면 더 좋을 거 같음
5. 인사이트를 얻어보려고, 한 번 저거 가지고 공부해 보기로 함

설연휴 동안 해보기