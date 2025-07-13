#!/bin/bash

# Define base package path and directories
BASE_DIR="src/main/java/com/upaguru/sense"
ENTITY_PKG="$BASE_DIR/entity"
REPO_PKG="$BASE_DIR/repository"
SERVICE_PKG="$BASE_DIR/service"
IMPL_PKG="$SERVICE_PKG/impl"

mkdir -p "$ENTITY_PKG" "$REPO_PKG" "$SERVICE_PKG" "$IMPL_PKG"

# List of entity class names
ENTITIES=("PromptSessionLog" "FeedbackAnalysisResult" "CurriculumInsights")

for ENTITY in "${ENTITIES[@]}"; do
  # Repository interface
  cat <<EOF > "$REPO_PKG/${ENTITY}Repository.java"
package com.upaguru.sense.repository;

import com.upaguru.sense.entity.${ENTITY};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ${ENTITY}Repository extends JpaRepository<${ENTITY}, UUID> {
}
EOF

  # Service interface
  cat <<EOF > "$SERVICE_PKG/${ENTITY}Service.java"
package com.upaguru.sense.service;

import com.upaguru.sense.entity.${ENTITY};

import java.util.List;
import java.util.UUID;

public interface ${ENTITY}Service {
    ${ENTITY} save(${ENTITY} entity);
    ${ENTITY} findById(UUID id);
    List<${ENTITY}> findAll();
}
EOF

  # Service implementation
  cat <<EOF > "$IMPL_PKG/${ENTITY}ServiceImpl.java"
package com.upaguru.sense.service.impl;

import com.upaguru.sense.entity.${ENTITY};
import com.upaguru.sense.repository.${ENTITY}Repository;
import com.upaguru.sense.service.${ENTITY}Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ${ENTITY}ServiceImpl implements ${ENTITY}Service {

    private final ${ENTITY}Repository repository;

    @Override
    public ${ENTITY} save(${ENTITY} entity) {
        return repository.save(entity);
    }

    @Override
    public ${ENTITY} findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<${ENTITY}> findAll() {
        return repository.findAll();
    }
}
EOF

done

echo "✅ Repository, Service, and Impl generated for: ${ENTITIES[*]}"
