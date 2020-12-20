package metaspace;

public class Practice {
// Heap : Eden, Old, PermGen(64~128mb, 상대적으로 작다. 동적으로 클래스를 많이 사용할 때 PermGen 이 꽉차는 경우 -> 늘리는 것보다 코드를 찾아 해결)
// Java8 : Heap PermGen 영역 사라짐 -> Native 영역(MetaSpace)에 자리 잡음 -> MaxMetaSpaceSize, 모니터링 하면서 값 설정 한다.
}
