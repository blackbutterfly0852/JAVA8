package optional;

import java.util.Optional;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;
    // public Optional<Progress> progress -> 설계의 문제
    public Progress progress;



    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Progress getProgress() {
        return progress;
    }

    // Optional 은 리턴타입으로만 사용하는 것이 좋다.
    public Optional<Progress> getProgressOptional() {
        return Optional.ofNullable(progress);
    }


    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    //  set 에서 Optional 을 사용하는 문제점
    // 1) 코드의 복잡성
    // 2) 클라이언트 코드에서 Optional<Progress> 자체가 NULL이 넘어 올 수있음 -> isPresent() 사용시 NPE 오류 발생
    public void setProgressOptional(Optional<Progress> progress) {
        progress.ifPresent((p) -> this.progress = p);
    }
}
