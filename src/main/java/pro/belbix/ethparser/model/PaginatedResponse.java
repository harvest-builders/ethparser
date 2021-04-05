package pro.belbix.ethparser.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaginatedResponse<T extends List<?>> {

  private int currentPage;
  private int nextPage;
  private int previousPage;
  private int totalPages;
  private T data;

}
