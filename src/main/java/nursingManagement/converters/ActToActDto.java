package nursingManagement.converters;

import nursingManagement.dto.ActDto;
import nursingManagement.persistence.model.Act;
import org.springframework.stereotype.Component;

@Component
public class ActToActDto extends AbstractConverter<Act, ActDto> {

    @Override
    public ActDto convert(Act act) {

        ActDto actDto = new ActDto();

        actDto.setNursing_date(act.getNursing_date());
        actDto.setMinimum_blood_pressure(act.getMinimum_blood_pressure());
        actDto.setMaximum_blood_pressure(act.getMaximum_blood_pressure());
        actDto.setHeart_beat(act.getHeart_beat());
        actDto.setComments(act.getComments());

        return actDto;
    }
}
