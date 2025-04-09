package hello.core.singletone;

public class SingletoneService {

    //자기자신을 인스턴스로 하나 static으로 가지고 있음(class레벨에 올라가기 때문에 딱 하나만 존재)
    private static final SingletoneService singletoneService = new SingletoneService();

    //이 객체 인스턴스가 필요하면 오직 getInstance() 를 통해서만 조회 가능. 항상 같은 인스턴스를 반환한다.
    public static SingletoneService getInstance() {
        return singletoneService;
    }

    //생성자를 private으로 막아서 new로 인스턴스를 생성 못하게 한다
    private SingletoneService() {
    }

    public void logic() {
        System.out.println("Singletone Logic");
    }
}
