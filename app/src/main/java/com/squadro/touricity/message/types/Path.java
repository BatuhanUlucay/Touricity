package com.squadro.touricity.message.types;

import com.squadro.touricity.message.types.interfaces.IPath;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Path extends AbstractEntry implements IPath {

    private String path_id;
    private String path_type;
    private List<PathVertex> vertices;

    public Path(String entry_id, int expense, int duration, String comment, String path_id,
                String path_type, List<PathVertex> vertices) {
        super(entry_id, expense, duration, comment);
        this.path_id = path_id;
        this.path_type = path_type;
        this.vertices = vertices;
    }
}
