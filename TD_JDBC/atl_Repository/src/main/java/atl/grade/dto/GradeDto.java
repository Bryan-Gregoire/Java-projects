package atl.grade.dto;

/**
 *
 * @author jlc
 */
public class GradeDto extends Dto<Integer> {

    private int value;
    private String lesson;

    public GradeDto(Integer key) {
        super(key);
    }

    public int getValue() {
        return value;
    }

    public String getLesson() {
        return lesson;
    }

}
