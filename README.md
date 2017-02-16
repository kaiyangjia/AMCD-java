# AMCD-java
AMCD(Asynchronous Multiple Condtions Dependence) 模式的java版本实现

### 使用实例

```java
//in class Main
//init the repo Demo, and add some conditions
ConditionRepoManager manager = ConditionRepoManager.getInstance();
manager.obtain("Demo");
manager.add("condition1", "condition2", "condition3");

Runnable runnable = new Runnable{
    public void run(){
        //do what you want
    };
}
manager.setTarget();

```

```java
//in class B
ConditionRepoManager manager = ConditionRepoManager.getInstance();
if (manager.complete("condition1")) {
    manager.getRepo("Demo").execTarget(new ExecCallback{
        public void onComplete(){
            //you already done what you want to do
        };

        public void onError(){

        };

        public void onResult(A result){

        };
    });
}
```

```java
//in class C
ConditionRepoManager manager = ConditionRepoManager.getInstance();
if (manager.complete("condition1")) {
    manager.getRepo("Demo").execTarget(new ExecCallback{
        public void onComplete(){
            //you already done what you want to do
        };

        public void onError(){

        };

        public void onResult(A result){

        };
    });
}
```

```java
//in class C
ConditionRepoManager manager = ConditionRepoManager.getInstance();
if (manager.complete("condition1")) {
    manager.getRepo("Demo").execTarget(new ExecCallback{
        public void onComplete(){
            //you already done what you want to do
        };

        public void onError(){

        };

        public void onResult(A result){

        };
    });
}
```

```java
//in class N ...
```

OK ,that's all.I know it is easy.So , Good Luck!