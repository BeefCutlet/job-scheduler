package site.jobiljeong.scheduler.entity.custom;

public enum ScheduleType {

    MEETING("면접"),
    PROFILE("서류심사"),
    CODING_TEST("코딩테스트"),
    ETC("기타");

    private String type;

    ScheduleType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
