package site.jobiljeong.scheduler.entity.custom;

public enum AttachmentCategory {
    RESUME("이력서"),
    COVER_LETTER("자기소개서"),
    PORTFOLIO("포트폴리오");

    private final String type;


    AttachmentCategory(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
