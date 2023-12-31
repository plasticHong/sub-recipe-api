package com.hong.repository.sub.custom;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.hong.dto.Request.RecipeSearchCondition;
import com.hong.dto.response.data.RecipeData;
import com.hong.entity.sub.QMemberFavoriteRecipe;
import com.hong.entity.sub.QRecipe;
import com.hong.entity.sub.QSandwichBase;
import com.hong.entity.sub.member.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomRecipeRepoImpl implements CustomRecipeRepo {

    private final JPAQueryFactory queryFactory;

    private final QRecipe recipe = QRecipe.recipe;
    private final QMember member = QMember.member;
    private final QSandwichBase sandwichBase = QSandwichBase.sandwichBase;
    private final QMemberFavoriteRecipe favoriteRecipe = QMemberFavoriteRecipe.memberFavoriteRecipe;


    @Override
    public Page<RecipeData> findMemberFavoriteRecipe(Long memberId,Pageable pageable) {
        List<RecipeData> list = selectRecipeData()
                .from(favoriteRecipe)
                .join(recipe).on(recipe.id.eq(favoriteRecipe.recipeId))
                .join(member).on(member.id.eq(recipe.memberId))
                .join(sandwichBase).on(sandwichBase.id.eq(recipe.sandwichBaseId))
                .where(
                        recipe.useYn.isTrue(),
                        favoriteRecipe.memberId.eq(memberId)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(recipe.count())
                .from(favoriteRecipe)
                .join(recipe).on(recipe.id.eq(favoriteRecipe.recipeId))
                .where(recipe.useYn.isTrue(), favoriteRecipe.memberId.eq(memberId))
                .fetchOne();

        if (count!=null){
            return new PageImpl<>(list,pageable,count);
        }
        return new PageImpl<>(list);
    }

    @Override
    public Page<RecipeData> findRecipe(OrderSpecifier<?> orderCondition, RecipeSearchCondition searchCondition, Pageable pageable) {

        List<RecipeData> list = selectRecipeData()
                .from(recipe)
                .join(member).on(member.id.eq(recipe.memberId))
                .join(sandwichBase).on(sandwichBase.id.eq(recipe.sandwichBaseId))
                .orderBy(orderCondition)
                .where(
                        recipe.useYn.isTrue(),
                        eqSandwichBaseId(searchCondition.getSandwichBaseId()),
                        eqMemberId(searchCondition.getMemberId()),
                        withOutCucumber(searchCondition.getIsWithOutCucumber()),

                        loeMaxKcal(searchCondition.getMaxKcal()),
                        goeMinKcal(searchCondition.getMinKcal()),

                        loeMaxFat(searchCondition.getMaxFat()),
                        goeMinFat(searchCondition.getMinFat()),

                        loeMaxProtein(searchCondition.getMaxProtein()),
                        goeMinProtein(searchCondition.getMinProtein()),

                        loeMaxPrice(searchCondition.getMaxPrice()),
                        goeMinPrice(searchCondition.getMinPrice())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory.select(recipe.count())
                .from(recipe)
                .where(recipe.useYn.isTrue(),
                        eqSandwichBaseId(searchCondition.getSandwichBaseId()),
                        eqMemberId(searchCondition.getMemberId()),
                        withOutCucumber(searchCondition.getIsWithOutCucumber()),

                        loeMaxKcal(searchCondition.getMaxKcal()),
                        goeMinKcal(searchCondition.getMinKcal()),

                        loeMaxFat(searchCondition.getMaxFat()),
                        goeMinFat(searchCondition.getMinFat()),

                        loeMaxProtein(searchCondition.getMaxProtein()),
                        goeMinProtein(searchCondition.getMinProtein()),

                        loeMaxPrice(searchCondition.getMaxPrice()),
                        goeMinPrice(searchCondition.getMinPrice()))
                .fetchOne();

        if (count!=null){
            return new PageImpl<>(list,pageable,count);
        }
        return new PageImpl<>(list);
    }

    private JPAQuery<RecipeData> selectRecipeData() {
        return queryFactory.select(Projections.bean(RecipeData.class,
                        recipe.id,
                        recipe.title,
                        recipe.memberId,
                        member.nickName.as("ownerNickname"),
                        recipe.sandwichBaseId,
                        sandwichBase.korName.as("sandwichBaseName"),
                        sandwichBase.image.as("sandwichBaseImagePath"),
                        recipe.totalPrice,
                        recipe.totalKcal,
                        recipe.totalProtein,
                        recipe.totalFat,
                        recipe.jmtPoint,
                        recipe.respectPoint,
                        recipe.createTime
                ));
    }

    private BooleanExpression withOutCucumber(Boolean isWithOutCucumber) {
        if (isWithOutCucumber == null || !isWithOutCucumber) return null;

        final Long cucumberId = 3L;
        final Long pickleId = 6L;

        return recipe.veggie1.isNull().or(recipe.veggie1.ne(cucumberId).and(recipe.veggie1.ne(pickleId)))
                .and(
                        recipe.veggie2.isNull().or(recipe.veggie2.ne(cucumberId).and(recipe.veggie2.ne(pickleId)))
                )
                .and(
                        recipe.veggie3.isNull().or(recipe.veggie3.ne(cucumberId).and(recipe.veggie3.ne(pickleId)))
                )
                .and(
                        recipe.veggie4.isNull().or(recipe.veggie4.ne(cucumberId).and(recipe.veggie4.ne(pickleId)))
                )
                .and(
                        recipe.veggie5.isNull().or(recipe.veggie5.ne(cucumberId).and(recipe.veggie5.ne(pickleId)))
                )
                .and(
                        recipe.veggie6.isNull().or(recipe.veggie6.ne(cucumberId).and(recipe.veggie6.ne(pickleId)))
                )
                .and(
                        recipe.veggie7.isNull().or(recipe.veggie7.ne(cucumberId).and(recipe.veggie7.ne(pickleId)))
                )
                .and(
                        recipe.veggie8.isNull().or(recipe.veggie8.ne(cucumberId).and(recipe.veggie8.ne(pickleId)))
                );

    }
    private BooleanExpression eqMemberId(final Long memberId) {
        if (memberId == null) return null;
        return recipe.memberId.eq(memberId);
    }

    private BooleanExpression eqSandwichBaseId(final Long sandwichBaseId) {
        if (sandwichBaseId == null) return null;
        return recipe.sandwichBaseId.eq(sandwichBaseId);
    }

    private BooleanExpression loeMaxKcal(final Double maxKcal) {
        if (maxKcal == null) return null;
        return recipe.totalKcal.loe(maxKcal);
    }

    private BooleanExpression goeMinKcal(final Double minKcal) {
        if (minKcal == null) return null;
        return recipe.totalKcal.goe(minKcal);
    }

    private BooleanExpression loeMaxFat(final Double maxFat) {
        if (maxFat == null) return null;
        return recipe.totalFat.loe(maxFat);
    }

    private BooleanExpression goeMinFat(final Double minFat) {
        if (minFat == null) return null;
        return recipe.totalFat.goe(minFat);
    }

    private BooleanExpression loeMaxProtein(final Double maxProtein) {
        if (maxProtein == null) return null;
        return recipe.totalProtein.loe(maxProtein);
    }

    private BooleanExpression goeMinProtein(final Double minProtein) {
        if (minProtein == null) return null;
        return recipe.totalProtein.goe(minProtein);
    }

    private BooleanExpression loeMaxPrice(final Integer maxPrice) {
        if (maxPrice == null) return null;
        return recipe.totalPrice.loe(maxPrice);
    }

    private BooleanExpression goeMinPrice(final Integer minPrice) {
        if (minPrice == null) return null;
        return recipe.totalPrice.goe(minPrice);
    }

}
